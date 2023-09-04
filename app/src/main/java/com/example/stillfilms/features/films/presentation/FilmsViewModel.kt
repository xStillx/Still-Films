package com.example.stillfilms.features.films.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stillfilms.features.films.domain.SearchInteractor
import com.example.stillfilms.features.films.domain.model.Search
import com.example.stillfilms.features.splash.data.entity.FilmEntity
import com.example.stillfilms.features.splash.data.entity.FilmsDataBase
import com.example.stillfilms.features.splash.data.entity.FilmsRepository
import com.example.stillfilms.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val _search = MutableLiveData<ViewState<Search>>()
    val search get() = _search.asLiveData()

    private val _isLoad = MutableLiveData<Boolean>()
    val isLoad get() = _isLoad.asLiveData()

    var readAllData: LiveData<List<FilmEntity>>? = null
    private var repository: FilmsRepository? = null

    val getSavedPosition = SingleLiveEvent<Int>()

    fun database(context: Context) {
        val filmsDao = FilmsDataBase.getDataBase(context = context).filmsDao()
        repository = FilmsRepository(filmsDao)
        readAllData = repository!!.readAllData
    }

    fun getData() {
        readAllData = repository!!.readAllData
    }

    fun updateFilm(film: FilmEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.update(film)
        }
    }

    fun addToFavorite(film: FilmEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.addFilm(film)
        }
    }

    fun deleteFromFavorite(film: FilmEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.deleteFilm(film)
        }
    }

    fun searchFilm(name: String) {
        _isLoad.value = true
        viewModelScope.launch {
            _search.value = searchInteractor.searchFilm(name).asViewState()
            _isLoad.value = false
        }
    }

    fun getSavedInstanceState(savedPosition: Int) {
        getSavedPosition.call(savedPosition)
    }
}