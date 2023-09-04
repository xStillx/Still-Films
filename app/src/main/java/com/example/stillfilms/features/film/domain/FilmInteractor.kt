package com.example.stillfilms.features.film.domain

import com.example.stillfilms.features.film.data.FilmRepo
import com.example.stillfilms.utils.safeRequest
import javax.inject.Inject

class FilmInteractor @Inject constructor(
    private val filmRepo: FilmRepo
) {

    suspend fun getFilm(id: String) = safeRequest{
        filmRepo.getFilm(id)
    }
}