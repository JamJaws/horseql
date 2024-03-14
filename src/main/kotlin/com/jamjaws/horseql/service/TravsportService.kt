package com.jamjaws.horseql.service

import com.jamjaws.horseql.integration.travsport.model.horse.TravsportHorse
import com.jamjaws.horseql.integration.travsport.model.offspring.TravsportOffspring
import com.jamjaws.horseql.integration.travsport.model.pedigree.TravsportPedigree
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Service
class TravsportService(@Qualifier("webClientTravsport") private val webClient: WebClient) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Cacheable("getHorseBasicInformation", cacheManager = "longTimeCacheManager")
    fun getHorseBasicInformation(id: Long): TravsportHorse? {
        log.info("get horse basic information for id: $id")
        return webClient.get().uri("/horses/basicinformation/organisation/TROT/sourceofdata/SPORT/horseid/$id")
            .retrieve()
            .bodyToMono<TravsportHorse>()
            .onErrorResume(WebClientResponseException.NotFound::class.java) { Mono.empty() }
            .block()
    }

    @Cacheable("getHorsePedigree", cacheManager = "longTimeCacheManager")
    fun getHorsePedigree(id: Long): TravsportPedigree? {
        log.info("get horse pedigree for id: $id")
        return webClient.get()
            .uri("/horses/pedigree/organisation/TROT/sourceofdata/SPORT/horseid/$id?pedigreeTree=SMALL")
            .retrieve()
            .bodyToMono<TravsportPedigree>()
            .onErrorResume(WebClientResponseException.NotFound::class.java) { Mono.empty() }
            .block()
    }

    @Cacheable("getHorseOffspring", cacheManager = "longTimeCacheManager")
    fun getHorseOffspring(id: Long, genderCode: String): TravsportOffspring? {
        log.info("get horse offspring for id: $id")
        return webClient.get()
            .uri("/horses/offspring/organisation/TROT/sourceofdata/SPORT/horseid/$id?genderCode=$genderCode")
            .retrieve()
            .bodyToMono<TravsportOffspring>()
            .onErrorResume(WebClientResponseException.NotFound::class.java) { Mono.empty() }
            .block()
    }
}
