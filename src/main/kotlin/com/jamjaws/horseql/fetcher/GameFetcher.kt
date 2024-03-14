package com.jamjaws.horseql.fetcher

import com.jamjaws.horseql.codegen.types.Game
import com.jamjaws.horseql.service.GameService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class GameFetcher(
    private val gameService: GameService,
) {
    @DgsQuery
    fun game(@InputArgument id: String): Game? = gameService.getGame(id)

}
