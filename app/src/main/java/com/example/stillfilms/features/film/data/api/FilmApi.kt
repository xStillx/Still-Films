package com.example.stillfilms.features.film.data.api

import com.example.stillfilms.features.film.data.model.FilmResponse
import com.example.stillfilms.utils.KeyUtils
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FilmApi {
    @Headers("X-API-KEY: ${KeyUtils.API_KEY}")
    @GET("v1.3/movie/{id}")
    suspend fun getFilm(@Path("id") id: String): FilmResponse
}