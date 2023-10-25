package com.iesam.features.superHeros.data.remote.api.hero

import com.iesam.features.superHeros.domain.models.Hero
import retrofit2.Response

import retrofit2.http.GET

interface HeroApiService{
    @GET("https://dam.sitehub.es/api-curso/superheroes/heroes.json")
    suspend fun getHeroFeed(): Response<List<HeroApiModel>>

}