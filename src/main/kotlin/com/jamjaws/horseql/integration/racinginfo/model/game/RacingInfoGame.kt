package com.jamjaws.horseql.integration.racinginfo.model.game

import com.fasterxml.jackson.annotation.JsonProperty

data class RacingInfoGame(
    val id: String,
    val status: String,
    val pools: Pools,
    val races: List<Race>,
    val version: Long,
    val newBettingSystem: Boolean,
    val type: String,
)

data class Pools(
    @JsonProperty("V86")
    val v86: V86,
)

data class V86(
    @JsonProperty("@type")
    val type: String,
    val id: String,
    val status: String,
    val timestamp: String,
    val turnover: Long,
    val betType: String,
    val harry: Boolean,
    val systemCount: Long,
    val payouts: Payouts,
    val jackpotAmount: Long,
)

data class Payouts(
    @JsonProperty("6")
    val n6: Long,
    @JsonProperty("7")
    val n7: Long,
    @JsonProperty("8")
    val n8: Long,
)

data class Race(
    val id: String,
    val name: String?,
    val date: String,
    val number: Long,
    val distance: Long,
    val startMethod: String,
    val startTime: String,
    val scheduledStartTime: String,
    val prize: String,
    val terms: List<String>,
    val sport: String,
    val track: Track,
    val status: String,
    val pools: Pools2,
    val starts: List<Start>,
    val mergedPools: List<MergedPool>,
)

data class Track(
    val id: Long,
    val name: String,
    val condition: String,
    val countryCode: String,
)

data class Pools2(
    val vinnare: Vinnare,
    val plats: Plats,
)

data class Vinnare(
    @JsonProperty("@type")
    val type: String,
    val id: String,
    val status: String,
    val timestamp: String,
    val turnover: Long,
    val betType: String,
)

data class Plats(
    @JsonProperty("@type")
    val type: String,
    val id: String,
    val status: String,
    val timestamp: String,
    val turnover: Long,
    val betType: String,
)

data class Start(
    val id: String,
    val number: Long,
    val postPosition: Long,
    val distance: Long,
    val horse: Horse,
    val driver: Driver,
    val pools: Pools3,
    val scratched: Boolean?,
)

data class Horse(
    val id: Long,
    val name: String,
    val nationality: String?,
    val age: Long,
    val sex: String,
    val record: Record,
    val trainer: Trainer,
    val shoes: Shoes,
    val sulky: Sulky,
    val money: Long,
    val color: String,
    val owner: Owner,
    val breeder: Breeder,
    val statistics: Statistics2,
    val pedigree: Pedigree,
    val foreignOwned: Boolean?,
    val homeTrack: HomeTrack2?,
)

data class Record(
    val code: String,
    val startMethod: String,
    val distance: String,
    val time: Time,
)

data class Time(
    val minutes: Long,
    val seconds: Long,
    val tenths: Long,
)

data class Trainer(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val shortName: String,
    val location: String,
    val birth: Long,
    val license: String,
    val silks: String?,
    val homeTrack: HomeTrack?,
    val statistics: Statistics?,
)

data class HomeTrack(
    val id: Long,
    val name: String,
)

data class Statistics(
    val years: Years,
)

data class Years(
    @JsonProperty("2023")
    val n2023: n2023,
    @JsonProperty("2024")
    val n2024: n2024,
)

data class n2023(
    val starts: Long,
    val earnings: Long,
    val placement: Placement,
    val winPercentage: Long,
)

data class Placement(
    @JsonProperty("3")
    val n3: Long,
    @JsonProperty("2")
    val n2: Long,
    @JsonProperty("1")
    val n1: Long,
)

data class n2024(
    val starts: Long,
    val earnings: Long,
    val placement: Placement2,
    val winPercentage: Long,
)

data class Placement2(
    @JsonProperty("3")
    val n3: Long,
    @JsonProperty("2")
    val n2: Long,
    @JsonProperty("1")
    val n1: Long,
)

data class Shoes(
    val reported: Boolean,
    val front: Front?,
    val back: Back?,
)

data class Front(
    val hasShoe: Boolean,
    val changed: Boolean?,
)

data class Back(
    val hasShoe: Boolean,
    val changed: Boolean?,
)

data class Sulky(
    val reported: Boolean,
    val type: Type?,
    val colour: Colour,
)

data class Type(
    val code: String,
    val text: String,
    val engText: String,
    val changed: Boolean,
)

data class Colour(
    val code: String,
    val text: String,
    val engText: String,
    val changed: Boolean,
)

data class Owner(
    val id: Long,
    val name: String,
    val location: String?,
)

data class Breeder(
    val id: Long,
    val name: String,
    val location: String?,
)

data class Statistics2(
    val years: Years2,
    val life: Life,
    val lastFiveStarts: LastFiveStarts,
)

data class Years2(
    @JsonProperty("2023")
    val n2023: n20232,
    @JsonProperty("2024")
    val n2024: n20242,
)

data class n20232(
    val starts: Long,
    val earnings: Long,
    val placement: Placement3,
    val records: List<Record2>,
)

data class Placement3(
    @JsonProperty("3")
    val n3: Long,
    @JsonProperty("2")
    val n2: Long,
    @JsonProperty("1")
    val n1: Long,
)

data class Record2(
    val code: String,
    val startMethod: String,
    val distance: String,
    val time: Time2,
    val place: Long,
)

data class Time2(
    val minutes: Long,
    val seconds: Long,
    val tenths: Long,
)

data class n20242(
    val starts: Long,
    val earnings: Long,
    val placement: Placement4,
    val records: List<Record3>,
)

data class Placement4(
    @JsonProperty("3")
    val n3: Long,
    @JsonProperty("2")
    val n2: Long,
    @JsonProperty("1")
    val n1: Long,
)

data class Record3(
    val code: String,
    val startMethod: String,
    val distance: String,
    val time: Time3,
    val place: Long,
)

data class Time3(
    val minutes: Long,
    val seconds: Long,
    val tenths: Long,
)

data class Life(
    val starts: Long,
    val earnings: Long,
    val placement: Placement5,
    val records: List<Record4>,
    val winPercentage: Long,
    val placePercentage: Long,
    val earningsPerStart: Long,
    val startPoints: Long,
)

data class Placement5(
    @JsonProperty("3")
    val n3: Long,
    @JsonProperty("2")
    val n2: Long,
    @JsonProperty("1")
    val n1: Long,
)

data class Record4(
    val code: String,
    val startMethod: String,
    val distance: String,
    val time: Time,
    val place: Long,
    val year: String,
)

data class Time4(
    val minutes: Long,
    val seconds: Long,
    val tenths: Long,
)

data class LastFiveStarts(
    val averageOdds: Long,
)

data class Pedigree(
    val father: Father,
    val mother: Mother,
    val grandfather: Grandfather,
)

data class Father(
    val id: Long,
    val name: String,
    val nationality: String?,
)

data class Mother(
    val id: Long,
    val name: String,
    val nationality: String?,
)

data class Grandfather(
    val id: Long,
    val name: String,
    val nationality: String?,
)

data class HomeTrack2(
    val id: Long,
    val name: String,
)

data class Driver(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val shortName: String,
    val location: String,
    val birth: Long,
    val license: String,
    val silks: String,
    val homeTrack: HomeTrack3?,
    val statistics: Statistics3?,
)

data class HomeTrack3(
    val id: Long,
    val name: String,
)

data class Statistics3(
    val years: Years3,
)

data class Years3(
    @JsonProperty("2023")
    val n2023: n20233,
    @JsonProperty("2024")
    val n2024: n20243,
)

data class n20233(
    val starts: Long,
    val earnings: Long,
    val placement: Placement6,
    val winPercentage: Long,
)

data class Placement6(
    @JsonProperty("3")
    val n3: Long,
    @JsonProperty("2")
    val n2: Long,
    @JsonProperty("1")
    val n1: Long,
)

data class n20243(
    val starts: Long,
    val earnings: Long,
    val placement: Placement7,
    val winPercentage: Long,
)

data class Placement7(
    @JsonProperty("3")
    val n3: Long,
    @JsonProperty("2")
    val n2: Long,
    @JsonProperty("1")
    val n1: Long,
)

data class Pools3(
    val vinnare: Vinnare2,
    val plats: Plats2,
    @JsonProperty("V86")
    val v86: V862,
)

data class Vinnare2(
    @JsonProperty("@type")
    val type: String,
    val odds: Long,
)

data class Plats2(
    @JsonProperty("@type")
    val type: String,
    val minOdds: Long,
    val maxOdds: Long,
)

data class V862(
    @JsonProperty("@type")
    val type: String,
    val betDistribution: Long,
)

data class MergedPool(
    val name: String,
    val betTypes: List<String>,
)
