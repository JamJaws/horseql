package com.jamjaws.horseql.integration.travsport.model.horse

data class TravsportHorse(
    val organisation: String?,
    val sourceOfData: String?,
    val id: Long?,
    val name: String?,
    val horseGender: HorseGender?,
    val horseBreed: HorseBreed?,
    val color: String?,
    val registrationNumber: String?,
    val uelnNumber: String?,
    val passport: String?,
    val registrationCountryCode: String?,
    val bredCountryCode: String?,
    val birthCountryCode: String?,
    val dateOfBirth: String?,
    val dateOfBirthDisplayValue: String?,
    val trotAdditionalInformation: TrotAdditionalInformation?,
    val guestHorse: Boolean?,
    val registrationStatus: RegistrationStatus?,
    val owner: Owner?,
    val breeder: Breeder?,
    val trainer: Trainer?,
    val representative: Representative?,
    val offspringExists: Boolean?,
    val resultsExists: Boolean?,
    val historyExists: Boolean?,
    val breedingEvaluationExists: Boolean?,
    val sportInfoType: String?,
)

data class HorseGender(
    val code: String?,
    val text: String?,
)

data class HorseBreed(
    val code: String?,
    val text: String?,
)

data class TrotAdditionalInformation(
    val mockInlander: Boolean?,
    val limitedStartPrivileges: Boolean?,
    val embryo: Boolean?,
    val offspringStartsExists: Boolean?,
    val registrationDone: Boolean?,
)

data class RegistrationStatus(
    val changeable: Boolean?,
    val dead: Boolean?,
)

data class Owner(
    val organisation: String?,
    val sourceOfData: String?,
    val id: Long?,
    val linkable: Boolean?,
    val name: String?,
    val ownershipForm: String?,
    val header: String?,
)

data class Breeder(
    val organisation: String?,
    val sourceOfData: String?,
    val id: Long?,
    val linkable: Boolean?,
    val name: String?,
)

data class Trainer(
    val organisation: String?,
    val sourceOfData: String?,
    val id: Long?,
    val licenseType: String?,
    val name: String?,
    val linkable: Boolean?,
)

data class Representative(
    val organisation: String?,
    val sourceOfData: String?,
    val id: Long?,
    val personType: String?,
    val name: String?,
)
