package adamatti

import com.google.common.base.Predicates
import org.ff4j.FF4j
import org.ff4j.web.FF4jDispatcherServlet
import org.ff4j.web.embedded.ConsoleServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
@SuppressWarnings("unused")
@ComponentScan(value = ["org.ff4j.spring.boot.web.api", "org.ff4j.services", "org.ff4j.aop", "org.ff4j.spring"])
class Config : SpringBootServletInitializer() {
    @Bean
    fun getFF4j() = FF4j("ff4j.xml")

    @Bean
    fun servletRegistrationBean(ff4jConsoleServlet: ConsoleServlet) = ServletRegistrationBean(ff4jConsoleServlet, "/ff4j-console")

    @Bean
    fun getFF4jServlet(ff4j: FF4j): ConsoleServlet{
        val ff4jConsoleServlet = ConsoleServlet()
        ff4jConsoleServlet.ff4j = ff4j
        return ff4jConsoleServlet
    }

    @Bean
    fun ff4jDispatcherServletRegistrationBean(ff4jDispatcherServlet: FF4jDispatcherServlet) : ServletRegistrationBean<FF4jDispatcherServlet> {
        val registration = ServletRegistrationBean(ff4jDispatcherServlet, "/ff4j-web-console/*")
        registration.setLoadOnStartup(1)
        return registration
    }

    @Bean
    fun getFF4jDispatcherServlet(ff4j: FF4j): FF4jDispatcherServlet {
        val ff4jConsoleServlet = FF4jDispatcherServlet()
        ff4jConsoleServlet.ff4j = ff4j
        return ff4jConsoleServlet
    }

    @Bean
    fun swagger() = Docket(DocumentationType.SWAGGER_2)
        .select()
        .paths(Predicates.not(PathSelectors.regex("/error|/info|/health.*")))
        .build()
}
