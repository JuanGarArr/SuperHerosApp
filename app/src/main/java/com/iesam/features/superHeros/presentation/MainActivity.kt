package com.iesam.features.superHeros.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.iesam.features.superHeros.data.HeroDataRepository
import com.iesam.features.superHeros.data.local.XmlHeroDataSource
import com.iesam.features.superHeros.data.remote.HeroRemoteDataSource
import com.iesam.superheros.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch { getHeroFeed() }
    }

    suspend fun getHeroFeed(){
        val heroes = HeroDataRepository(XmlHeroDataSource(this), HeroRemoteDataSource())
        val heroFeed=heroes.getHeroFeed()
        Log.d("@dev","Heroes=$heroFeed")
    }
}