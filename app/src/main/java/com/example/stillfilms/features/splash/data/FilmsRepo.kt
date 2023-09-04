package com.example.stillfilms.features.splash.data

import com.example.stillfilms.features.splash.data.api.FilmsApi
import com.example.stillfilms.features.splash.data.model.mapper.FilmsMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmsRepo @Inject constructor(
    private val api: FilmsApi,
    private val filmsMapper: FilmsMapper
) {

    suspend fun getFilms() = withContext(Dispatchers.IO){
        api.getFilms().let {
            filmsMapper.map(it)
        }
    }
}