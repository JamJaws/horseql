package com.jamjaws.horseql.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilderFactory

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

}
