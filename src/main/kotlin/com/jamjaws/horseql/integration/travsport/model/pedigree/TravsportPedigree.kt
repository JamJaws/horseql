package com.jamjaws.horseql.integration.travsport.model.pedigree

data class TravsportPedigree(
    val organisation: String?,
    val sourceOfData: String?,
    val horseId: Long?,
    val father: Horse?,
    val mother: Horse?,
)

data class Horse(
    val organisation: String?,
    val sourceOfData: String?,
    val horseId: Long?,
    val name: String?,
    val registrationNumber: String?,
    val record: String?,
    val linkable: Boolean?,
)
