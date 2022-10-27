package com.progressapp.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName ("results") var movieList: List<MovieResponse> = emptyList()
)

data class MovieResponse (

    @SerializedName ("adult") var adultType : Boolean,
    @SerializedName ("release_date") var relaseDate : String,
    @SerializedName ("id") var MovieId: Long,
    @SerializedName ("original_title")var originalTitle : String,
    @SerializedName ("overview") var movieDescription : String,
    @SerializedName ("poster_path") var movieImage : String

    )