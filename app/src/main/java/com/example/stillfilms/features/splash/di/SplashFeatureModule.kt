package com.example.stillfilms.features.splash.di

import com.example.stillfilms.features.splash.data.api.FilmsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class SplashFeatureModule {

    @Provides
    fun provideFilmsApi(retrofit: Retrofit) = retrofit.create(
        FilmsApi::class.java
    )
}