package com.example.stillfilms.features.favorites.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stillfilms.features.splash.data.entity.FilmEntity
import com.example.stillfilms.features.splash.data.entity.FilmsDataBase
import com.example.stillfilms.features.splash.data.entity.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(): ViewModel(){

    var readAllData: LiveData<List<FilmEntity>>? = null
    private var repository: FilmsRepository? = null

    fun database(context: Context) {
        val filmsDao = FilmsDataBase.getDataBase(context = context).filmsDao()
        repository = FilmsRepository(filmsDao)
        readAllData = repository!!.readAllData
    }

    fun updateFilm(film: FilmEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.update(film)
        }
    }
}