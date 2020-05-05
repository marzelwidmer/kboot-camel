package ch.keepcalm.demo.routes

import ch.keepcalm.demo.processor.HeaderProcessor
import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

@Component
class FileRouteBuilder : RouteBuilder() {

    private val workDir =System.getenv("PWD")
    private val input = "$workDir/orders/in?include=order.xml"
    private val output = "$workDir/orders/out?fileExist=Fail"

    @Throws(Exception::class)
    override fun configure() {
        from("file:$input")
            .process(HeaderProcessor())
            .to("file:$output")
            .log("Camel body: \${body.class} \${body}")

        from("file:$workDir/orders/in?include=order-.*xml")
            .process(HeaderProcessor())
            .to("file:$workDir/orders/out?fileName=\${header.orderDate}-\${header.CamelFileName}")
            .log("Camel body: \${body.class} \${body}")
    }
}
