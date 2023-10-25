package com.iesam.features.superHeros.domain

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.features.superHeros.domain.models.Hero

interface SuperHeroRepository {

    suspend fun getHeroFeed():Either<ErrorApp, List<Hero>>

}