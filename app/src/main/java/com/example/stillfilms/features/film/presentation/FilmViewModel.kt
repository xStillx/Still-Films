package com.example.stillfilms.features.film.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stillfilms.features.film.domain.FilmInteractor
import com.example.stillfilms.features.film.domain.model.Film
import com.example.stillfilms.features.splash.data.entity.FilmEntity
import com.example.stillfilms.features.splash.data.entity.FilmsDataBase
import com.example.stillfilms.features.splash.data.entity.FilmsRepository
import com.example.stillfilms.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmInteractor: FilmInteractor
): ViewModel(){

    private val _film = MutableLiveData<ViewState<Film>>()
    val film get() = _film.asLiveData()

    private val _isLoad = MutableLiveData<Boolean>()
    val isLoad get() = _isLoad.asLiveData()

    val goToBack = SingleLiveEvent<Unit>()

    private var repository: FilmsRepository? = null

    fun getFilm(id: String){
        _isLoad.value = true
        viewModelScope.launch {
            _film.value = filmInteractor.getFilm(id).asViewState()
            _isLoad.value = false
        }
    }

    fun goToBack(){
        goToBack.call()
    }

    fun dataBase(context: Context){
        val foodDao = FilmsDataBase.getDataBase(context = context).filmsDao()
        repository = FilmsRepository(foodDao)
    }

    fun updateFavorite(film: FilmEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.update(film)
        }
    }

    fun addToFavorite(film: FilmEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.addFilm(film)
        }
    }
}