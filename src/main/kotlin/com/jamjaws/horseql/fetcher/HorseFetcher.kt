package com.jamjaws.horseql.fetcher

import com.jamjaws.horseql.codegen.DgsConstants
import com.jamjaws.horseql.codegen.types.Horse
import com.jamjaws.horseql.codegen.types.RaceHorse
import com.jamjaws.horseql.service.TravsportService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import graphql.schema.DataFetchingEnvironment


@DgsComponent
class HorseFetcher(
    private val travsportService: TravsportService,
) {
    @DgsQuery
    fun horse(@InputArgument id: Long): Horse? =
        travsportService.getHorseBasicInformation(id)?.let { Horse(it.id, it.name) }

    @DgsData(parentType = DgsConstants.HORSE.TYPE_NAME)
    fun mother(dfe: DataFetchingEnvironment): Horse? {
        val horse = dfe.getSource<Horse>()
        return horse.id?.let(::getMother)
    }

    @DgsData(parentType = DgsConstants.RACEHORSE.TYPE_NAME, field = DgsConstants.RACEHORSE.Mother)
    fun raceMother(dfe: DataFetchingEnvironment): Horse? {
        val horse = dfe.getSource<RaceHorse>()
        return horse.id?.let(::getMother)
    }

    private fun getMother(horseId: Long): Horse? =
        travsportService.getHorsePedigree(horseId)
            ?.mother
            ?.horseId
            ?.let(travsportService::getHorseBasicInformation)
            ?.let { Horse(it.id, it.name) }

    @DgsData(parentType = DgsConstants.HORSE.TYPE_NAME)
    fun father(dfe: DataFetchingEnvironment): Horse? {
        val horse = dfe.getSource<Horse>()
        return horse.id?.let(::getFather)
    }

    @DgsData(parentType = DgsConstants.RACEHORSE.TYPE_NAME, field = DgsConstants.RACEHORSE.Father)
    fun raceFather(dfe: DataFetchingEnvironment): Horse? {
        val horse = dfe.getSource<RaceHorse>()
        return horse.id?.let(::getFather)
    }

    private fun getFather(horseId: Long): Horse? =
        travsportService.getHorsePedigree(horseId)
            ?.father
            ?.horseId
            ?.let(travsportService::getHorseBasicInformation)
            ?.let { Horse(it.id, it.name) }

    @DgsData(parentType = DgsConstants.HORSE.TYPE_NAME)
    fun siblings(dfe: DataFetchingEnvironment): List<Horse>? {
        val horse = dfe.getSource<Horse>()
        return horse?.id?.let(travsportService::getHorsePedigree)
            ?.let { listOf(it.mother?.horseId, it.father?.horseId) }
            ?.asSequence()
            ?.filterNotNull()
            ?.mapNotNull { travsportService.getHorseOffspring(it, "test") }
            ?.map { it.offspring.orEmpty() }
            ?.flatten()
            ?.distinctBy { it.horse?.id }
            ?.filter { it.horse?.id != horse.id }
            ?.map { Horse(it.horse?.id, it.horse?.name) }
            ?.toList()

    }

}
