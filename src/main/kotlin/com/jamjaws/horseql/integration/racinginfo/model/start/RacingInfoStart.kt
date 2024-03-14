package com.jamjaws.horseql.integration.racinginfo.model.start

data class RacingInfoStart(
    val raceId: String?,
    val startNumber: Long?,
    val horse: Horse?,
)

data class Horse(
    val results: Results?,
)

data class Results(
    val records: List<Record>,
    val hasMoreRecords: Boolean?,
)

data class Record(
    val date: String?,
    val link: Boolean?,
    val kmTime: KmTime?,
    val odds: Long?,
    val place: String?,
    val mediaId: String?,
    val race: Race?,
    val track: Track?,
    val start: Start?,
    val galloped: Boolean?,
    val disqualified: Boolean?,
    val oddsCode: String?,
    val scratched: Boolean?,
)

data class KmTime(
    val minutes: Long?,
    val seconds: Long?,
    val tenths: Long?,
    val code: String?,
)

data class Race(
    val id: String?,
    val sport: String?,
    val type: String?,
    val number: Long?,
    val startMethod: String?,
    val firstPrize: Long?,
)

data class Track(
    val id: Long?,
    val name: String?,
    val condition: String?,
    val countryCode: String?,
)

data class Start(
    val distance: Long?,
    val postPosition: Long?,
    val driver: Driver?,
    val horse: Horse2,
)

data class Driver(
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val shortName: String?,
)

data class Horse2(
    val shoes: Shoes?,
    val sulky: Sulky?,
)

data class Shoes(
    val front: Boolean?,
    val back: Boolean?,
)

data class Sulky(
    val type: Type?,
)

data class Type(
    val code: String?,
    val text: String?,
)
