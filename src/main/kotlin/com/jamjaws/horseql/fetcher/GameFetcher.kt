package com.jamjaws.horseql.fetcher

import com.jamjaws.horseql.codegen.DgsConstants
import com.jamjaws.horseql.codegen.types.Game
import com.jamjaws.horseql.codegen.types.Horse
import com.jamjaws.horseql.codegen.types.Result
import com.jamjaws.horseql.service.GameService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import graphql.execution.DataFetcherResult
import graphql.schema.DataFetchingEnvironment


@DgsComponent
class GameFetcher(
    private val gameService: GameService,
) {
    @DgsQuery
    fun game(@InputArgument id: String): DataFetcherResult<Game?> {
        val game = gameService.getGame(id)

        return DataFetcherResult.newResult<Game>().data(game).localContext(
            game?.races?.filterNotNull()?.mapNotNull { race ->
                race.starts?.filterNotNull()?.mapNotNull { start ->
                    val horseId = start.horse?.id
                    if (horseId != null && race.id != null && start.number != null) {
                        HorseStart(horseId, race.id, start.number)
                    } else {
                        null
                    }
                }
            }?.flatten()?.let(::GameQueryContext)
        ).build()
    }

    data class GameQueryContext(val horseStarts: List<HorseStart>)

    data class HorseStart(val horseId: Long, val raceId: String, val startNumber: Long)

    @DgsData(parentType = DgsConstants.RACEHORSE.TYPE_NAME)
    fun results(dfe: DataFetchingEnvironment): List<Result> {
        val source = dfe.getSource<Horse>()
        val localContext = dfe.getLocalContext<GameQueryContext>()
        return localContext.horseStarts.firstOrNull { it.horseId == source.id }?.let { horseStart ->
            gameService.getHorseResults(horseStart.raceId, horseStart.startNumber)
        }.orEmpty()
    }

}
