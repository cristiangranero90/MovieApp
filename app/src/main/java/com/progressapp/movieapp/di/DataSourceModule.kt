package com.progressapp.movieapp.di

import android.content.Context
import androidx.room.Room
import com.progressapp.movieapp.dao.MovieDao
import com.progressapp.movieapp.data.DbDataSource
import com.progressapp.movieapp.data.RestDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideRetrofit(@Named("BASE_URL") baseUrl : String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun restDataSource(retrofit: Retrofit) : RestDataSource =
        retrofit.create(RestDataSource::class.java)

    @Provides
    @Singleton
    fun dbDataSource(@ApplicationContext context: Context) : DbDataSource{
        return Room.databaseBuilder(context, DbDataSource::class.java, "user_movies")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun movieDao(db: DbDataSource) : MovieDao = db.movieDao()

}