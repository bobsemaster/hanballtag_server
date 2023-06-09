package de.schreib.handball.handballtag

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.filter.CommonsRequestLoggingFilter


@SpringBootApplication
class HandballtagApplication {
    @Bean
    fun requestLoggingFilter(): CommonsRequestLoggingFilter {
        val loggingFilter = CommonsRequestLoggingFilter()
        loggingFilter.setIncludeQueryString(true)
        loggingFilter.setIncludeClientInfo(true)
        // loggingFilter.setMaxPayloadLength(1000);
        loggingFilter.setIncludePayload(false)
        loggingFilter.setBeforeMessagePrefix(">")
        loggingFilter.setBeforeMessagePrefix("<")
        return loggingFilter
    }


}

fun main(args: Array<String>) {
    runApplication<HandballtagApplication>(*args)
}
