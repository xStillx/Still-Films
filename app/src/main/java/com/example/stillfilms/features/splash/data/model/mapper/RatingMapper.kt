package com.example.stillfilms.features.splash.data.model.mapper

import com.example.stillfilms.features.splash.data.model.RatingRes
import com.example.stillfilms.features.splash.domain.model.Rating
import javax.inject.Inject

class RatingMapper @Inject constructor() {

    fun map(ratingRes: RatingRes) = Rating(
        imdb = ratingRes.imdb
    )
}