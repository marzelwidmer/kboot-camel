package ch.keepcalm.demo.processor

import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.slf4j.LoggerFactory

class ExchangePrinter : Processor {

    private val log = LoggerFactory.getLogger(javaClass)

    @Throws(Exception::class)
    override fun process(exchange: Exchange?) {
        val body = exchange?.`in`?.body
        log.info("Body: $body")
    }
}