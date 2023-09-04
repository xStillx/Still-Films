package com.example.stillfilms.features.film.data.model.mapper

import com.example.stillfilms.features.film.data.model.PremiereRes
import com.example.stillfilms.features.film.domain.model.Premiere
import javax.inject.Inject

class PremiereMapper @Inject constructor() {

    fun map(premiereRes: PremiereRes) = Premiere(
        world = premiereRes.world,
        russia = premiereRes.russia
    )
}