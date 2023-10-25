package com.iesam.features.superHeros.domain

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.features.superHeros.domain.models.Work

interface WorkRepository {
    fun getWorkByHeroId(idHero:Int): Either<ErrorApp, Work>

}