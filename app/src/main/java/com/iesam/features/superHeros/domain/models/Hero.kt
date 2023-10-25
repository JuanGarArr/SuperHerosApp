package com.iesam.features.superHeros.domain.models

data class Hero (
    val id : Int,
    val heroName : String,
    val biography: Biography,
    val work: Work,
    val img:List<String>){
        fun getUrlImageX():String=img[0]
        fun getUrlImageS():String=img[1]
        fun getUrlImageM():String=img[2]
        fun getUrlImageL():String=img[3]
    }



data class Biography(
    val fullName: String,
    val alterEgos: String,
    val placeOfBirth: String
)

data class Work(
    val occupation: String,
    val base: String
)





