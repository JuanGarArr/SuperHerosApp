package com.iesam.features.superHeros.domain

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.features.superHeros.domain.models.Work

interface WorkRepository {

    fun getWork(): Either<ErrorApp, Work>

    fun getWorkFeed(): Either<ErrorApp, List<Work>>
}