package com.example.stillfilms.features.film.di

import com.example.stillfilms.features.film.data.api.FilmApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class FilmFeatureModule {

    @Provides
    fun provideFilmApi(retrofit: Retrofit) = retrofit.create(
        FilmApi::class.java
    )
}