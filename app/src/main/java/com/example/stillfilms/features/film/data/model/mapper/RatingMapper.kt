package com.example.stillfilms.features.film.data.model.mapper

import com.example.stillfilms.features.film.data.model.RatingRes
import com.example.stillfilms.features.film.domain.model.Rating
import javax.inject.Inject

class RatingMapper @Inject constructor() {

    fun map(ratingRes: RatingRes) = Rating(
        imdb = ratingRes.imdb
    )
}