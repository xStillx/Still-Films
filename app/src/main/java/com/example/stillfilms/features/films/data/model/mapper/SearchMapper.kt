package com.example.stillfilms.features.films.data.model.mapper

import com.example.stillfilms.features.films.data.model.SearchRes
import com.example.stillfilms.features.films.domain.model.Search
import javax.inject.Inject

class SearchMapper @Inject constructor(
    private val docMapper: DocMapper
) {

    fun map(searchRes: SearchRes) = Search(
        docs = searchRes.docs.map {
            docMapper.map(it)
        }
    )
}