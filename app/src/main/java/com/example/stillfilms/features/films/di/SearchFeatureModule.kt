package com.example.stillfilms.features.films.di

import com.example.stillfilms.features.films.data.api.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class SearchFeatureModule {

    @Provides
    fun provideSearchApi(retrofit: Retrofit) = retrofit.create(
        SearchApi::class.java
    )
}