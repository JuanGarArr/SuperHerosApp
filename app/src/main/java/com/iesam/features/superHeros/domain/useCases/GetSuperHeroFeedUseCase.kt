package com.iesam.features.superHeros.domain.useCases

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.features.superHeros.domain.SuperHeroRepository
import com.iesam.features.superHeros.domain.models.Hero

class GetSuperHeroFeedUseCase(private val repository: SuperHeroRepository) {
    operator fun invoke():Either<ErrorApp, List<Hero>>{
        return repository.getHeroFeed()
    }
}