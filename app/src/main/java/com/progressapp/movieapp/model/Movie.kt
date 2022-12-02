package com.progressapp.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    val title: String,
    val poster_path: String,
    val idtmdb: Int,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
