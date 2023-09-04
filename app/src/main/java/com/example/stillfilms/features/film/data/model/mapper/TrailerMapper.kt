package com.example.stillfilms.features.film.data.model.mapper

import com.example.stillfilms.features.film.data.model.TrailerRes
import com.example.stillfilms.features.film.domain.model.Trailer
import javax.inject.Inject

class TrailerMapper @Inject constructor() {

    fun map(trailerRes: TrailerRes) = Trailer(
        url = trailerRes.url
    )
}