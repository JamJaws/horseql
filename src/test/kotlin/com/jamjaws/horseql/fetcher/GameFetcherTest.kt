package com.jamjaws.horseql.fetcher

import com.netflix.graphql.dgs.DgsQueryExecutor
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockserver.client.MockServerClient
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MockServerContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.DockerImageName


@SpringBootTest
@ContextConfiguration(initializers = [GameFetcherTest.Initializer::class])
class GameFetcherTest(
    @Autowired
    private val dgsQueryExecutor: DgsQueryExecutor
) {
    companion object {
        private val mockServerImage: DockerImageName = DockerImageName.parse("mockserver/mockserver")
            .withTag("mockserver-" + MockServerClient::class.java.getPackage().implementationVersion)

        private val mockServer: MockServerContainer = MockServerContainer(mockServerImage).apply {
            waitingFor(Wait.forHttp("/").forStatusCode(404))
        }
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            mockServer.start()
            TestPropertyValues.of(
                "horseql.racinginfo.protocol=http",
                "horseql.racinginfo.host=${mockServer.host}",
                "horseql.racinginfo.port=${mockServer.firstMappedPort}",
            ).applyTo(configurableApplicationContext.environment)
        }
    }


    @BeforeEach
    fun setUp() {
        MockServerClient(mockServer.host, mockServer.firstMappedPort)
            .`when`(
                request().withMethod("GET").withPath("/services/racinginfo/v1/api/games/V86_2024-03-13_40_1")
            ).respond(
                response()
                    .withHeader("Content-Type", "application/json")
                    .withBody(javaClass.getResource("/V86_2024-03-13_40_1.json")?.readBytes())
            )
    }

    @Test
    fun `get game version by id`() {
        val version: Long = dgsQueryExecutor.executeAndExtractJsonPath(
            """
                {
                    game(id: "V86_2024-03-13_40_1") {
                        version
                    }
                }
            """.trimIndent(), "data.game.version"
        )
        version shouldBe 1710334391413
    }
}
