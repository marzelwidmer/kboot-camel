package ch.keepcalm.demo.routes

import ch.keepcalm.demo.processor.ExchangePrinter
import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

/**
 * https://www.walkerit.ch/public-ftp
 *
 * FTP-Server: ftp.walkerit.ch
 * Benutzername: public
 * Passwort: Public8852
 */
@Component
class FtpRouteBuilder : RouteBuilder() {


    private val ftpEndpoint ="ftp.walkerit.ch"
    private val username ="public"
    private val password ="Public8852"

    private val workDir =System.getenv("PWD")
    private val output = "$workDir/orders/out?fileExist=Fail"


    @Throws(Exception::class)
    override fun configure() {
         from("ftp://$ftpEndpoint?username=$username&password=$password&delete=true&include=order.*xml")
             .log("New File \${header.CamleFileName} picked up from \${header.CamleFileHost}")
             .process(ExchangePrinter())
             .to("file://$output")
    }
}
