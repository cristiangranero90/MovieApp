package com.progressapp.movieapp.di

import com.progressapp.movieapp.data.RestDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    @Named("API_KEY")
    fun provideApiKey() = "api_key=cb86974c362f47d464bb3b6c94b8f7c2&language=en-US&page=1"

    @Provides
    @Singleton
    fun provideRetrofit(@Named("BASE_URL") baseUrl : String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun restDataSource(retrofit: Retrofit) : RestDataSource =
        retrofit.create(RestDataSource::class.java)

}