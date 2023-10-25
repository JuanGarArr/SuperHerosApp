package com.iesam.features.superHeros.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.app.error.ErrorApp
import com.iesam.features.superHeros.domain.useCases.GetSuperHeroFeedUseCase
import kotlinx.coroutines.launch

class HeroViewModel(
    private val getSuperHeroFeedUseCase: GetSuperHeroFeedUseCase

) : ViewModel() {

    data class UiState(
        val errorApp: ErrorApp? = null,
        val hero: List<GetSuperHeroFeedUseCase.HeroesList>? = null
    )

    private val _uiState=MutableLiveData<UiState>()
    val uiState:LiveData<UiState> = _uiState

    fun getHeroes(){
        viewModelScope.launch {
            getSuperHeroFeedUseCase.invoke().fold(
                {responseError(it)},
                {responseSucces(it)}
            )

        }
    }
    fun responseSucces(hero: List<GetSuperHeroFeedUseCase.HeroesList>?){
        _uiState.postValue(UiState(hero = hero))
    }
    fun responseError(errorApp: ErrorApp){
        _uiState.postValue(UiState(errorApp = errorApp))
    }



}