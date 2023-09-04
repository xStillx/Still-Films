package com.example.stillfilms.features.film.data.model.mapper

import com.example.stillfilms.features.film.data.model.PersonRes
import com.example.stillfilms.features.film.domain.model.Person
import javax.inject.Inject

class PersonMapper @Inject constructor() {

    fun map(personRes: PersonRes) = Person(
        photo = personRes.photo,
        name = personRes.name,
        description = personRes.description
    )
}