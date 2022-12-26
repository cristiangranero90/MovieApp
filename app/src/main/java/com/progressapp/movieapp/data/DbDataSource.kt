package com.progressapp.movieapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.progressapp.movieapp.dao.MovieDao
import com.progressapp.movieapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class DbDataSource : RoomDatabase() {

    abstract fun movieDao() : MovieDao
}