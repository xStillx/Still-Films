package com.example.stillfilms.features.splash.data.entity

import androidx.lifecycle.LiveData

class FilmsRepository(private val filmsDao: FilmsDao) {

    val readAllData: LiveData<List<FilmEntity>> = filmsDao.readData()

    suspend fun addFilm(film: FilmEntity){
        filmsDao.addFilm(film)
    }

    suspend fun deleteFilm(film: FilmEntity){
        filmsDao.deleteFilm(film)
    }

    suspend fun clear(){
        filmsDao.clear()
    }

    suspend fun update(film: FilmEntity){
        filmsDao.updateFilm(film)
    }
}