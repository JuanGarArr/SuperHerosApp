package com.iesam.features.superHeros.data.remote.api.hero

import com.google.gson.annotations.SerializedName
import com.iesam.features.superHeros.domain.models.Biography
import com.iesam.features.superHeros.domain.models.Work

data class HeroApiModel (
        @SerializedName("id") val id:Int,
        @SerializedName("heroName") val heroName:String,
        @SerializedName("biography") val biography: Biography,
        @SerializedName("work") val work: Work

        )