package com.iesam.features.superHeros.data.local

import android.content.Context
import com.google.gson.Gson
import com.iesam.app.error.ErrorApp
import com.iesam.app.functional.Either
import com.iesam.app.functional.left
import com.iesam.app.functional.right
import com.iesam.features.superHeros.domain.models.Hero

class XmlHeroDataSource (private val context: Context){

    val sharedPref = context.getSharedPreferences("Heroes", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun getHeroFeed():Either<ErrorApp, List<Hero>>{
        return try {
            val jsonUsers = sharedPref.all as Map<String, String>
            val hero = jsonUsers.values.map {
                gson.fromJson(it, Hero::class.java)
            }
            return getHeroFeed()
        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }

}