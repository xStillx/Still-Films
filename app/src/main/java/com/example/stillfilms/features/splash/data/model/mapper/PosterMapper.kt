package com.example.stillfilms.features.splash.data.model.mapper

import com.example.stillfilms.features.splash.data.model.PosterRes
import com.example.stillfilms.features.splash.domain.model.Poster
import javax.inject.Inject

class PosterMapper @Inject constructor() {

    fun map(posterRes: PosterRes) = Poster(
        previewUrl = posterRes.previewUrl
    )
}