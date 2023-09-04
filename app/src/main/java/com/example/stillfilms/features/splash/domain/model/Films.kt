package com.example.stillfilms.features.splash.domain.model

data class Films(
    val docs: List<Doc>
)

data class Doc(
    val id: Long,
    val rating: Rating,
    val name: String,
    val poster: Poster
)

data class Rating(
    val imdb: Double
)

data class Poster(
    val previewUrl: String
)
