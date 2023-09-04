package com.example.stillfilms.features.splash.domain

import com.example.stillfilms.features.splash.data.FilmsRepo
import com.example.stillfilms.utils.safeRequest
import javax.inject.Inject

class FilmsInteractor @Inject constructor(
    private val filmsRepo: FilmsRepo
) {

    suspend fun getFilms() = safeRequest {
        filmsRepo.getFilms()
    }
}