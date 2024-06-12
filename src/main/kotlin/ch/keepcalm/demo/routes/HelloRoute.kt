package ch.keepcalm.demo.routes

import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

@Component
class HelloRoute : RouteBuilder() {

    @Throws(Exception::class)
    override fun configure() {
        from("direct:greeting").id("greeting")
            .log("Hello ${body()}")
            .choice()
                .`when`(simple("\${body} contains 'Camel'"))
                    .log("Hello Camel")
                .otherwise()
                    .log("Hello stranger")
            .end()
            .to("direct:finishGreeting")

        from("direct:finishGreeting").id("finishGreeting")
            .log("Finish greeting")

    }
}