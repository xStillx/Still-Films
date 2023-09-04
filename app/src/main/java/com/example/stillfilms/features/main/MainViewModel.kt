package com.example.stillfilms.features.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stillfilms.features.splash.data.entity.FilmsDataBase
import com.example.stillfilms.features.splash.data.entity.FilmsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel(){

    private var repository: FilmsRepository? = null

    fun dataBase(context: Context){
        val foodDao = FilmsDataBase.getDataBase(context = context).filmsDao()
        repository = FilmsRepository(foodDao)
    }

    fun clearDatabase(){
        viewModelScope.launch {
            repository!!.clear()
        }
    }
}