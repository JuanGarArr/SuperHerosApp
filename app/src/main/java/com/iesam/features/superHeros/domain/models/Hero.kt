package com.iesam.features.superHeros.domain.models

data class Hero (
    val id : Int,
    val heroName : String,
    val biography: Biography,
    val work: Work
)


data class Biography(
    val fullName: String,
    val alterEgos: String,
    val placeOfBirth: String
)

data class Work(
    val occupation: String,
    val base: String
)





