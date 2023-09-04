package com.example.stillfilms.features.films.data

import com.example.stillfilms.features.films.data.api.SearchApi
import com.example.stillfilms.features.films.data.model.mapper.SearchMapper
import com.example.stillfilms.features.splash.data.model.mapper.FilmsMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepo @Inject constructor(
    private val api: SearchApi,
    private val searchMapper: SearchMapper
) {

    suspend fun searchFilm(name: String) = withContext(Dispatchers.IO){
        api.searchFilm(name).let {
            searchMapper.map(it)
        }
    }
}