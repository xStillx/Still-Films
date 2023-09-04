package com.example.stillfilms.features.splash.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_table")
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val rating: Double,
    val name: String,
    val poster: String?,
    var isFavorite: Boolean
)