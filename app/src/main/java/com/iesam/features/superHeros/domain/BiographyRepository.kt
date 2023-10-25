package com.iesam.features.superHeros.domain

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.features.superHeros.domain.models.Biography

interface BiographyRepository {

    fun getBiograpgyByHeroId(idHero:Int): Either<ErrorApp, Biography>

}