package com.jamjaws.horseql.service

import com.jamjaws.horseql.codegen.types.Breeder
import com.jamjaws.horseql.codegen.types.Driver
import com.jamjaws.horseql.codegen.types.Game
import com.jamjaws.horseql.codegen.types.HomeTrack
import com.jamjaws.horseql.codegen.types.Horse
import com.jamjaws.horseql.codegen.types.Owner
import com.jamjaws.horseql.codegen.types.Race
import com.jamjaws.horseql.codegen.types.Record
import com.jamjaws.horseql.codegen.types.RecordTime
import com.jamjaws.horseql.codegen.types.Result
import com.jamjaws.horseql.codegen.types.Shoes
import com.jamjaws.horseql.codegen.types.ShoesFrontBack
import com.jamjaws.horseql.codegen.types.Start
import com.jamjaws.horseql.codegen.types.Sulky
import com.jamjaws.horseql.codegen.types.SulkyColour
import com.jamjaws.horseql.codegen.types.SulkyType
import com.jamjaws.horseql.codegen.types.Track
import com.jamjaws.horseql.codegen.types.Trainer
import com.jamjaws.horseql.integration.racinginfo.model.game.RacingInfoGame
import com.jamjaws.horseql.integration.racinginfo.model.start.RacingInfoStart
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono

@Service
class GameService(private val webClient: WebClient) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Cacheable("getGame")
    fun getGame(id: String): Game? {
        log.info("get racing info game $id")
        return webClient.get().uri("/v1/api/games/$id").retrieve()
            .bodyToMono(RacingInfoGame::class.java)
            .onErrorResume(WebClientResponseException.NotFound::class.java) { notFound -> Mono.empty() }
            .block()
            ?.let { game ->
                Game(
                    game.id,
                    game.status,
                    game.version,
                    game.races.map { race ->
                        Race(
                            id = race.id,
                            name = race.name,
                            date = race.date,
                            number = race.number,
                            distance = race.distance,
                            startMethod = race.startMethod,
                            startTime = race.startTime,
                            scheduledStartTime = race.scheduledStartTime,
                            prize = race.prize,
                            terms = race.terms,
                            sport = race.sport,
                            track = race.track.let { track ->
                                Track(
                                    id = track.id,
                                    name = track.name,
                                    condition = track.condition,
                                    countryCode = track.countryCode,
                                )
                            },
                            status = race.status,
                            starts = race.starts.map { start ->
                                Start(
                                    id = start.id,
                                    number = start.number,
                                    postPosition = start.postPosition,
                                    distance = start.distance,
                                    horse = start.horse.let { horse ->
                                        Horse(
                                            id = horse.id,
                                            name = horse.name,
                                            nationality = horse.nationality,
                                            age = horse.age,
                                            sex = horse.sex,
                                            record = horse.record.let { record ->
                                                Record(
                                                    code = record.code,
                                                    startMethod = record.startMethod,
                                                    distance = record.distance,
                                                    time = record.time.let { time ->
                                                        RecordTime(
                                                            time.minutes,
                                                            time.seconds,
                                                            time.tenths,
                                                        )
                                                    }
                                                )
                                            },
                                            trainer = horse.trainer.let { trainer ->
                                                Trainer(
                                                    id = trainer.id,
                                                    firstName = trainer.firstName,
                                                    lastName = trainer.lastName,
                                                    shortName = trainer.shortName,
                                                    location = trainer.location,
                                                    birth = trainer.birth,
                                                    license = trainer.license,
                                                    silks = trainer.silks,
                                                    homeTrack = trainer.homeTrack?.let { homeTrack ->
                                                        HomeTrack(id = homeTrack.id, name = homeTrack.name)
                                                    }
                                                )
                                            },
                                            shoes = horse.shoes.let { shoes ->
                                                Shoes(
                                                    reported = shoes.reported,
                                                    front = shoes.front.let {
                                                        ShoesFrontBack(
                                                            it?.hasShoe,
                                                            it?.changed
                                                        )
                                                    },
                                                    back = shoes.back.let { ShoesFrontBack(it?.hasShoe, it?.changed) }
                                                )
                                            },
                                            sulky = horse.sulky.let { sulky ->
                                                Sulky(
                                                    reported = sulky.reported,
                                                    type = sulky.type?.let {
                                                        SulkyType(
                                                            code = it.code,
                                                            text = it.text,
                                                            engText = it.engText,
                                                            changed = it.changed,
                                                        )
                                                    },
                                                    colour = sulky.colour.let {
                                                        SulkyColour(
                                                            code = it.code,
                                                            text = it.text,
                                                            engText = it.engText,
                                                            changed = it.changed,
                                                        )
                                                    }
                                                )
                                            },
                                            money = horse.money,
                                            color = horse.color,
                                            owner = horse.owner.let { owner ->
                                                Owner(
                                                    id = owner.id,
                                                    name = owner.name,
                                                    location = owner.location,
                                                )
                                            },
                                            breeder = horse.breeder.let { breeder ->
                                                Breeder(
                                                    id = breeder.id,
                                                    name = breeder.name,
                                                    location = breeder.location,
                                                )
                                            },
                                            homeTrack = horse.homeTrack?.let { homeTrack ->
                                                HomeTrack(
                                                    id = homeTrack.id,
                                                    name = homeTrack.name,
                                                )
                                            },
                                        )
                                    },
                                    driver = start.driver.let { driver ->
                                        Driver(
                                            id = driver.id,
                                            firstName = driver.firstName,
                                            lastName = driver.lastName,
                                            shortName = driver.shortName,
                                            location = driver.location,
                                            birth = driver.birth,
                                            license = driver.license,
                                            silks = driver.silks
                                        )
                                    },
                                    scratched = start.scratched,
                                )
                            }
                        )
                    },
                    game.newBettingSystem,
                    game.type,
                )
            }
    }

    @Cacheable("getHorseResults")
    fun getHorseResults(raceId: String, startNumber: Long): List<Result> {
        log.info("$raceId - $startNumber")
        val start = webClient.get().uri("/v1/api/races/$raceId/start/$startNumber").retrieve()
            .bodyToMono(RacingInfoStart::class.java).block()
        return start?.horse?.results?.records?.map {
            Result(it.date, it.place)
        }.orEmpty()
    }

}
