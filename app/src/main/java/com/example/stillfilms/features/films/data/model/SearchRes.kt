package com.example.stillfilms.features.films.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchRes(
    val docs: List<DocRes>
)

@JsonClass(generateAdapter = true)
data class DocRes(
    val id: Long,
    val rating: Double,
    val name: String,
    val poster: String?,
)