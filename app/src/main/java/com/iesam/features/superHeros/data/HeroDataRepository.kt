package com.iesam.features.superHeros.data

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.features.superHeros.data.local.XmlHeroDataSource
import com.iesam.features.superHeros.data.remote.HeroRemoteDataSource
import com.iesam.features.superHeros.domain.SuperHeroRepository
import com.iesam.features.superHeros.domain.models.Hero

class HeroDataRepository (
    private val local: XmlHeroDataSource,
    private val remote:HeroRemoteDataSource
) : SuperHeroRepository{

    override suspend fun getHeroFeed(): Either<ErrorApp, List<Hero>> {
        remote.getHeroFeed()

        var hero = local.getHeroFeed()
        hero.mapLeft { errorApp ->
            return remote.getHeroFeed().map {hero ->
            local.getHeroFeed()
            hero
            }
        }
        return hero

    }

}