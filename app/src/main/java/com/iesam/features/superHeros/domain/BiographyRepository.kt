package com.iesam.features.superHeros.domain

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.features.superHeros.domain.models.Biography

interface BiographyRepository {

    fun getBiograpgy(): Either<ErrorApp, Biography>

    fun getBiograpgyFeed(): Either<ErrorApp, List<Biography>>

}