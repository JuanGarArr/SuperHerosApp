package com.iesam.features.superHeros.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.iesam.app.extensions.setUrl
import com.iesam.features.superHeros.data.HeroDataRepository
import com.iesam.features.superHeros.data.local.XmlHeroDataSource
import com.iesam.features.superHeros.data.remote.HeroRemoteDataSource
import com.iesam.features.superHeros.domain.useCases.GetSuperHeroFeedUseCase
import com.iesam.superheros.R
import com.iesam.superheros.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private val viewModel:HeroViewModel by lazy{
        HeroViewModel(
            GetSuperHeroFeedUseCase(
                HeroDataRepository(
                    XmlHeroDataSource(
                        this,
                        GsonSerialization()
                    ), HeroRemoteDataSource()
                )
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch { getHeroFeed() }
    }

    fun setupObserver(){
        viewModel.getHeroes()
        val observer = Observer<HeroViewModel.UiState>{
            it.errorApp.apply {
                showError(it.errorApp)
            }
            it.tapa.apply {
                bindData(it.tapa)
                showView()
            }
            it.isLoading.apply {
                showLoading(it.isLoading)
            }
        }
        viewModel.uiState.observe(this, observer)
    }


    private fun setUpBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    suspend fun getHeroFeed(){
        val heroes = HeroDataRepository(XmlHeroDataSource(this), HeroRemoteDataSource())
        val heroFeed=heroes.getHeroFeed()
        Log.d("@dev","Heroes=$heroFeed")
    }

    fun bindData(hero: GetSuperHeroFeedUseCase.HeroesList?){
        binding.apply {
            heroView.heroName.text=hero?.name
            heroView.realName.text=hero?.fullName
            heroView.occupation.text=hero?.ocupation
            heroView.heroImg.setUrl(hero?.img)

        }
    }

    private fun showError(error:ErrorApp?) {
        binding.errorView.errorLayout.visible()
        binding.loadingView.loadingView.hide()
        when(error){
            ErrorApp.UnknowError -> binding.layoutWithouterrro.hide()
            else -> binding.layoutWithouterrro.hide()
        }
    }
    fun showView(){
        binding.errorView.errorLayout.hide()
        binding.loadingView.loadingView.hide()
        binding.layoutWithouterrro.visible()
    }

    fun showLoading(isLoading:Boolean){
        if(isLoading == true){
            binding.at.hide()
        }else{
            showView()
        }
    }
}