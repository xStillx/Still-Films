package com.example.stillfilms.features.films.data.api

import com.example.stillfilms.features.films.data.model.SearchRes
import com.example.stillfilms.features.splash.data.model.FilmsResponse
import com.example.stillfilms.utils.KeyUtils
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchApi {

    @Headers("X-API-KEY: ${KeyUtils.API_KEY}")
    @GET("v1.2/movie/search?page=1&limit=10&")
    suspend fun searchFilm(@Query("query") name: String): SearchRes
}