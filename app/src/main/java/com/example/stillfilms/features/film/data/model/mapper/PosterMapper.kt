package com.example.stillfilms.features.film.data.model.mapper

import com.example.stillfilms.features.film.data.model.PosterRes
import com.example.stillfilms.features.film.domain.model.Poster
import javax.inject.Inject

class PosterMapper @Inject constructor() {

    fun map(posterRes: PosterRes) = Poster(
            url = posterRes.url
        )
}