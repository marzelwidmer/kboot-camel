package ch.keepcalm.demo

import org.apache.camel.builder.RouteBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class KbootCamel

fun main(args: Array<String>) {
    runApplication<KbootCamel>(*args)
}

