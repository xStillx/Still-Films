package com.example.stillfilms.features.splash.data.model.mapper

import com.example.stillfilms.features.splash.data.model.DocRes
import com.example.stillfilms.features.splash.domain.model.Doc
import javax.inject.Inject

class DocMapper @Inject constructor(
    private val ratingMapper: RatingMapper,
    private val posterMapper: PosterMapper
) {

    fun map(docRes: DocRes) = Doc(
        id = docRes.id,
        name = docRes.name,
        rating = docRes.rating.let {
            ratingMapper.map(it)
        },
        poster = docRes.poster.let {
            posterMapper.map(it)
        }
    )
}