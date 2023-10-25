package com.iesam.features.superHeros.data.remote

import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.app.functional.left
import com.iesam.app.functional.right
import com.iesam.features.superHeros.data.remote.api.hero.HeroApiModel
import com.iesam.features.superHeros.data.remote.api.hero.HeroApiService
import com.iesam.features.superHeros.data.remote.api.hero.toModel
import com.iesam.features.superHeros.domain.models.Hero
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeoutException


class HeroRemoteDataSource {

    private val baseUrl = "https://dam.sitehub.es/api-curso/superheroes"

    suspend fun getHeroFeed(): Either<ErrorApp, List<Hero>>{

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client:OkHttpClient=OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val service : HeroApiService = retrofit.create(HeroApiService::class.java)

        try {
            val repos = service.getHeroFeed()

            if(repos.isSuccessful){
                val heroes = repos.body()!!.map {
                    it.toModel()
                }
                return heroes.right()
            }else{
                return ErrorApp.InternetError.left()
            }
        }catch (ex:TimeoutException){
            return ErrorApp.InternetError.left()
        }


    }


}