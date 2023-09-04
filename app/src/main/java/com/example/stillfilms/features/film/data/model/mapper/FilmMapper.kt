package com.example.stillfilms.features.film.data.model.mapper

import com.example.stillfilms.features.film.data.model.FilmResponse
import com.example.stillfilms.features.film.domain.model.Film
import javax.inject.Inject

class FilmMapper @Inject constructor(
    private val ratingMapper: RatingMapper,
    private val premiereMapper: PremiereMapper,
    private val posterMapper: PosterMapper,
    private val videoMapper: VideoMapper,
    private val personMapper: PersonMapper
) {

    fun map(film: FilmResponse) = Film(
        id = film.id,
        name = film.name,
        rating = film.rating.let {
            ratingMapper.map(it)
        },
        description = film.description,
        premiere = film.premiere.let {
            premiereMapper.map(it)
        },
        poster = film.poster.let {
            posterMapper.map(it)
        },
        videos = film.videos.let {
            videoMapper.map(it)
        },
        persons = film.persons.map {
            personMapper.map(it)
        },
    )
}