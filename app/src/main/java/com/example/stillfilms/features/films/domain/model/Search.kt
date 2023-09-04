package com.example.stillfilms.features.films.domain.model

data class Search(
    val docs: List<Doc>
)

data class Doc(
    val id: Long,
    val rating: Double,
    val name: String,
    val poster: String?
)
