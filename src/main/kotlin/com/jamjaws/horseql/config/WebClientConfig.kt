package com.jamjaws.horseql.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    @Value("\${horseql.racinginfo.protocol}") private val protocol: String,
    @Value("\${horseql.racinginfo.host}") private val host: String,
    @Value("\${horseql.racinginfo.port}") private val port: Int,
) {

    @Bean
    fun webClient(): WebClient = WebClient.builder()
        .baseUrl("$protocol://$host:$port/services/racinginfo")
        .codecs { configurer -> configurer.defaultCodecs().maxInMemorySize(20 * 1024 * 1024) }
        .build()


    @Bean
    fun webClientTravsport(): WebClient = WebClient.builder()
        .baseUrl("https://api.travsport.se/webapi")
        .codecs { configurer -> configurer.defaultCodecs().maxInMemorySize(20 * 1024 * 1024) }
        .build()

}
