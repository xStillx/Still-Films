package com.example.stillfilms.features.splash.data.model.mapper

import com.example.stillfilms.features.splash.data.model.FilmsResponse
import com.example.stillfilms.features.splash.domain.model.Films
import javax.inject.Inject

class FilmsMapper @Inject constructor(
    private val docMapper: DocMapper
) {

    fun map(filmsResponse: FilmsResponse) = Films(
        docs = filmsResponse.docs.map {
            docMapper.map(it)
        }
    )
}