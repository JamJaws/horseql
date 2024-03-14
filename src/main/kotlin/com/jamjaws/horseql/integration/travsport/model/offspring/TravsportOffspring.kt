package com.jamjaws.horseql.integration.travsport.model.offspring

data class TravsportOffspring(
    val organisation: String?,
    val sourceOfData: String?,
    val horseId: Long?,
    val classification: String?,
    val numberOfSwedishOffspring: String?,
    val numberOfStarts: String?,
    val numberOfWins: String?,
    val prizeMoney: String?,
    val offspring: List<Offspring>?,
)

data class Offspring(
    val horse: Horse?,
    val registrationNumber: String?,
    val gender: Gender?,
    val yearBorn: Long?,
    val prizeMoney: PrizeMoney?,
    val deadMarked: Boolean?,
    val registrationDone: Boolean?,
    val placements: String?,
    val firstPlaces: Long?,
    val secondPlaces: Long?,
    val thirdPlaces: Long?,
    val numberOfStarts: NumberOfStarts?,
    val remark: String?,
)

data class Horse(
    val organisation: String?,
    val sourceOfData: String?,
    val id: Long?,
    val name: String?,
    val linkable: Boolean?,
)

data class Gender(
    val code: String?,
    val text: String?,
)

data class PrizeMoney(
    val sortValue: Long?,
    val displayValue: String?,
)

data class NumberOfStarts(
    val sortValue: Long?,
    val displayValue: String?,
)
