package com.example.stillfilms.features.films.domain

import com.example.stillfilms.features.films.data.SearchRepo
import com.example.stillfilms.utils.safeRequest
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val searchRepo: SearchRepo
){

    suspend fun searchFilm(name: String) = safeRequest {
        searchRepo.searchFilm(name)
    }
}