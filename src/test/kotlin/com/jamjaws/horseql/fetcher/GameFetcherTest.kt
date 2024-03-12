package com.jamjaws.horseql.fetcher

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.netflix.graphql.dgs.autoconfig.DgsExtendedScalarsAutoConfiguration
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DgsAutoConfiguration::class, DgsExtendedScalarsAutoConfiguration::class, GameFetcher::class])
class GameFetcherTest(
    @Autowired
    private val dgsQueryExecutor: DgsQueryExecutor
) {

    @Test
    fun `get game version by id`() {
        val version: Long = dgsQueryExecutor.executeAndExtractJsonPath(
            """
                {
                    game(id: "V4_2024-03-12_8_6") {
                        version
                    }
                }
            """.trimIndent(), "data.game.version"
        )
        version shouldBe 1710251763368
    }
}
