package com.example.stillfilms.features.film.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class  FilmResponse(
    val description: String,
    val id: Int,
    val name: String,
    val persons: List<PersonRes>,
    val poster: PosterRes,
    val premiere: PremiereRes,
    val rating: RatingRes,
    val videos: VideosRes,
)

@JsonClass(generateAdapter = true)
data class RatingRes(
    val imdb: Double,
)

@JsonClass(generateAdapter = true)
data class PremiereRes(
    val russia: String,
    val world: String
)

@JsonClass(generateAdapter = true)
data class PosterRes(
    val url: String
)

@JsonClass(generateAdapter = true)
data class VideosRes(
    val trailers: List<TrailerRes>

)

@JsonClass(generateAdapter = true)
data class TrailerRes(
    val url: String
)

@JsonClass(generateAdapter = true)
data class PersonRes(
    val description: String?,
    val name: String?,
    val photo: String,
)