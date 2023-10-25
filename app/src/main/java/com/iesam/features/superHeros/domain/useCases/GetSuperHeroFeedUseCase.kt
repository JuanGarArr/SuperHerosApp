package com.iesam.features.superHeros.domain.useCases

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.features.superHeros.domain.BiographyRepository
import com.iesam.features.superHeros.domain.SuperHeroRepository
import com.iesam.features.superHeros.domain.WorkRepository
import com.iesam.features.superHeros.domain.models.Biography
import com.iesam.features.superHeros.domain.models.Hero

class GetSuperHeroFeedUseCase(
    private val hero: SuperHeroRepository,
    private val work:WorkRepository,
    private val biography: BiographyRepository
    ) {

    suspend fun invoke():Either<ErrorApp, List<Hero>>{
        val heroes = hero.getHeroFeed()

        val list = heroes.get()?.map { hero ->
            val work = work.get
        }
    }
}