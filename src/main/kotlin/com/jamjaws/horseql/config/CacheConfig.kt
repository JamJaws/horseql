package com.jamjaws.horseql.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration


@Configuration
class CacheConfig {

    @Bean
    @Primary
    fun cacheManager(): CacheManager =
        CaffeineCacheManager().apply {
            setCaffeine(
                Caffeine.newBuilder()
                    .expireAfterWrite(10.seconds.toJavaDuration())
                    .maximumSize(500)
            )
        }

    @Bean
    fun longTimeCacheManager(): CacheManager {
        return CaffeineCacheManager().apply {
            setCaffeine(
                Caffeine.newBuilder()
                    .expireAfterWrite(1.hours.toJavaDuration())
                    .maximumSize(5_000)
            )
        }
    }
}
