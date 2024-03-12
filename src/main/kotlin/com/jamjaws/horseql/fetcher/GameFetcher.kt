package com.jamjaws.horseql.fetcher

import com.jamjaws.horseql.codegen.types.Game
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument


@DgsComponent
class GameFetcher {

    private val games = listOf(
        Game("V4_2024-03-12_8_6", "bettable", 1710251763368, true, "V4")
    )

    @DgsQuery
    fun game(@InputArgument id: String): Game? = games.firstOrNull { it.id?.contains(id) == true }

    @DgsQuery
    fun games(@InputArgument id: String?): List<Game> {
        return if (id != null) {
            games.filter { it.id?.contains(id) == true }
        } else {
            games
        }
    }

}
