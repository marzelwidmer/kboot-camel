package ch.keepcalm.demo.routes

import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component


@Component
class ChoiceRouteBuilder : RouteBuilder() {

    private val workDir = System.getenv("PWD")
    private val input = "$workDir/orders/in/publisher?include=pub-.*xml"
    private val output = "$workDir/orders/out/publisher"

    private final val XPATH_SEARCH_ORLY = "order/orderItems/orderItem/orderItemPublisherName/text() = 'ORly'"
    private final val XPATH_SEARCH_PACKT = "order/orderItems/orderItem/orderItemPublisherName/text() = 'Packt'"

    @Throws(Exception::class)
    override fun configure() {
        from("file:$input")
            .to("log:ordersReceived")
            .choice()
                .`when`(xpath(XPATH_SEARCH_ORLY))
                .log("ORly received")
                .to("file:$output/orly")
            .`when`(xpath(XPATH_SEARCH_PACKT))
                .log("Packt received")
                .to("file:$output/packt")
            .otherwise()
                .log("Other received")
                .to("file:$output/others")
            .end()
    }

}
