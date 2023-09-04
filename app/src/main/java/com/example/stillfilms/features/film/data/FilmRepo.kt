package com.example.stillfilms.features.film.data

import com.example.stillfilms.features.film.data.api.FilmApi
import com.example.stillfilms.features.film.data.model.mapper.FilmMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmRepo @Inject constructor(
    private val api: FilmApi,
    private val filmMapper: FilmMapper
) {

    suspend fun getFilm(id: String) = withContext(Dispatchers.IO){
        api.getFilm(id).let {
            filmMapper.map(it)
        }
    }
}