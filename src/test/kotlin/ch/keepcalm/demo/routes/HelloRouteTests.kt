package ch.keepcalm.demo.routes

import org.apache.camel.CamelContext
import org.apache.camel.builder.AdviceWith.adviceWith
import org.apache.camel.builder.AdviceWithRouteBuilder
import org.apache.camel.test.spring.junit5.CamelSpringBootTest
import org.apache.camel.test.spring.junit5.MockEndpoints
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@CamelSpringBootTest
@SpringBootTest
@MockEndpoints
class HelloRouteTest {

    @Autowired
    lateinit var camelContext: CamelContext


    @Test
    fun testHelloRoute() {
        adviceWith(camelContext, "greeting") { adviceWithRouteBuilder: AdviceWithRouteBuilder ->
            adviceWithRouteBuilder.weaveAddLast().to("mock:result")
        }
        // Send a message to the direct:greeting endpoint
        val result = camelContext.createProducerTemplate().requestBody("direct:greeting", "Hello Camel", String::class.java)

        // Assertions
        assert(result.contains("Hello Camel")) { "Expected 'Hello Camel' in the result" }
        assert(!result.contains("Hello stranger")) { "Unexpected 'Hello stranger' in the result" }
    }
}
