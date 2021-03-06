package ch.keepcalm.demo.processor

import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.language.xpath.XPathBuilder
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class HeaderProcessor : Processor {

    private final val XPATH_DATE = "/order/orderDate/text()"

    @Throws(Exception::class)
    override fun process(exchange: Exchange?) {
        val oderXml = exchange?.`in`?.body
        val orderDateTime = XPathBuilder.xpath(XPATH_DATE).evaluate(exchange?.context, oderXml)
        val formattedOrderDate = getFormattedData(orderDateTime = orderDateTime)
        exchange?.`in`?.setHeader("orderDate", formattedOrderDate)
        exchange?.`in`?.setHeader("uuid", UUID.randomUUID().toString().take(4))
    }

    // TODO: 05.05.20 DirtyHarry Implementation
    private fun getFormattedData(orderDateTime: String) = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        .format(LocalDateTime.ofInstant(Instant.parse(orderDateTime), ZoneOffset.UTC))


}