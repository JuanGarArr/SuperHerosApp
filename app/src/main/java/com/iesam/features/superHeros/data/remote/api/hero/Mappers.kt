package com.iesam.features.superHeros.data.remote.api.hero

import com.iesam.features.superHeros.domain.models.Hero

fun HeroApiModel.toModel(): Hero=
    Hero(1, this.heroName, biography, this.work)