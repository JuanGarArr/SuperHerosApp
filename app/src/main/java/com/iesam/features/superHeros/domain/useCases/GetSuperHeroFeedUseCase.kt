package com.iesam.features.superHeros.domain.useCases

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.app.functional.left
import com.iesam.app.functional.right
import com.iesam.features.superHeros.domain.BiographyRepository
import com.iesam.features.superHeros.domain.SuperHeroRepository
import com.iesam.features.superHeros.domain.WorkRepository
import com.iesam.features.superHeros.domain.models.Biography
import com.iesam.features.superHeros.domain.models.Hero

class GetSuperHeroFeedUseCase(
    private val hero: SuperHeroRepository,
    private val work:WorkRepository,
    private val biography: BiographyRepository,
    ) {

    suspend fun invoke(): Either<ErrorApp, List<HeroesList>?> {
        val heroes = hero.getHeroFeed()

        val list = heroes.get()?.map { hero ->
            val work = work.getWorkByHeroId(hero.id)
            val biography = biography.getBiograpgyByHeroId(hero.id)
            HeroesList(
                hero.id,
                hero.heroName,
                work.get()!!.occupation,
                biography.get()!!.fullName,
                hero.getUrlImageL()
            )

        }
        return list.right()
    }


    data class HeroesList(
        val id: Int,
        val name: String,
        val fullName: String,
        val ocupation: String,
        val img: String
    )

}
