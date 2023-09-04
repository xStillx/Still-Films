package com.example.stillfilms.features.splash.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stillfilms.features.splash.data.entity.FilmEntity
import com.example.stillfilms.features.splash.data.entity.FilmsDataBase
import com.example.stillfilms.features.splash.data.entity.FilmsRepository
import com.example.stillfilms.features.splash.domain.FilmsInteractor
import com.example.stillfilms.features.splash.domain.model.Films
import com.example.stillfilms.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val filmsInteractor: FilmsInteractor
): ViewModel() {

    private val _films = MutableLiveData<ViewState<Films>>()
    val films get() = _films.asLiveData()

    val goToFilms = SingleLiveEvent<Unit>()

    private var repository: FilmsRepository? = null

    init {
        getFilms()
        goToMenu()
    }

    private fun goToMenu(){
        viewModelScope.launch {
            delay(2500)
            goToFilms.call()
        }
    }

    fun dataBase(context: Context){
        val foodDao = FilmsDataBase.getDataBase(context = context).filmsDao()
        repository = FilmsRepository(foodDao)
    }

    private fun getFilms(){
        viewModelScope.launch {
            _films.value = filmsInteractor.getFilms().asViewState()
        }
    }

    fun addFilmsToDataBase(film: FilmEntity){
        viewModelScope.launch {
            repository!!.addFilm(film)
        }
    }
}