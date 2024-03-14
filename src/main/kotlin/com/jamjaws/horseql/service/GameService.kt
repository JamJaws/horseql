package com.jamjaws.horseql.service

import com.jamjaws.horseql.codegen.types.Game
import com.jamjaws.horseql.codegen.types.Race
import com.jamjaws.horseql.integration.racinginfo.model.RacingInfoGame
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class GameService(private val webClient: WebClient) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Cacheable("game")
    fun getGame(id: String): Game? {
        log.info("get racing info game $id")
        return webClient.get().uri("/v1/api/games/$id").retrieve().bodyToMono(RacingInfoGame::class.java).block()
            ?.let { game: RacingInfoGame ->
                Game(
                    game.id,
                    game.status,
                    game.version,
                    game.races.map { Race(it.id, it.name) },
                    game.newBettingSystem,
                    game.type,
                )
            }

    }

}
