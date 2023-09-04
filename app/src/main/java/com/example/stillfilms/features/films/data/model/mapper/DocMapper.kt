package com.example.stillfilms.features.films.data.model.mapper

import com.example.stillfilms.features.films.data.model.DocRes
import com.example.stillfilms.features.films.domain.model.Doc
import javax.inject.Inject

class DocMapper @Inject constructor() {

    fun map(docRes: DocRes) = Doc(
        id = docRes.id,
        rating = docRes.rating,
        name = docRes.name,
        poster = docRes.poster
    )
}