package com.example.stillfilms.features.film.domain.model

data class Film(
    val id: Int,
    val name: String,
    val rating: Rating,
    val description: String,
    val premiere: Premiere,
    val poster: Poster,
    val videos: Video,
    val persons: List<Person>
)

data class Rating(
    val imdb: Double
)

data class Premiere(
    val world: String,
    val russia: String
)

data class Poster(
    val url: String
)

data class Video(
    val trailers: List<Trailer>
)

data class Trailer(
    val url: String
)

data class Person(
    val photo: String,
    val name: String?,
    val description: String?
)