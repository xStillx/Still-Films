package com.example.stillfilms.features.splash.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmsResponse(
    val docs: List<DocRes>
)

@JsonClass(generateAdapter = true)
data class DocRes(
    val id: Long,
    val rating: RatingRes,
    val name: String,
    val poster: PosterRes,
)

@JsonClass(generateAdapter = true)
data class RatingRes(
    val imdb: Double
)

@JsonClass(generateAdapter = true)
data class PosterRes(
    val previewUrl: String
)