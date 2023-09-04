package com.example.stillfilms.features.splash.data.api

import com.example.stillfilms.features.splash.data.model.FilmsResponse
import com.example.stillfilms.utils.KeyUtils.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers

interface FilmsApi {

    @Headers("X-API-KEY: $API_KEY")
    @GET("v1.3/movie?page=1&limit=30")
    suspend fun getFilms(): FilmsResponse
}