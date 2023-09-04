package com.example.stillfilms.features.film.data.model.mapper

import com.example.stillfilms.features.film.data.model.VideosRes
import com.example.stillfilms.features.film.domain.model.Video
import javax.inject.Inject

class VideoMapper @Inject constructor(
    private val trailerMapper: TrailerMapper
) {

    fun map(videosRes: VideosRes) = Video(
        trailers = videosRes.trailers.map {
            trailerMapper.map(it)
        }
    )
}