package com.iesam.features.superHeros.data.remote.api.hero

import com.google.gson.annotations.SerializedName
import com.iesam.features.superHeros.domain.models.Biography
import com.iesam.features.superHeros.domain.models.Work

data class HeroApiModel (
        @SerializedName("id") val id:Int,
        @SerializedName("heroName") val heroName:String,
        @SerializedName("images") val images: Images

        )

data class Images(
        @SerializedName("xs") val xs:String,
        @SerializedName("sm") val sm:String,
        @SerializedName("md") val md:String,
        @SerializedName("lg") val lg:String
)