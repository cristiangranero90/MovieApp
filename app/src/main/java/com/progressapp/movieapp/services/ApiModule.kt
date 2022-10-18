package com.progressapp.movieapp.services

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL : String = "https://api.themoviedb.org/3/"
    private const val API_KEY : String = "api_key=cb86974c362f47d464bb3b6c94b8f7c2&language=en-US&page=1"

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(app : Application) : String = API_KEY

    @Provides
    @Singleton
    fun apiServiceProvider(app : Application) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}