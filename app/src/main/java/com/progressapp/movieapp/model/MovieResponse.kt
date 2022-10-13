package com.progressapp.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieResponse (

    @SerializedName ("adult") var adultType : Boolean,
    @SerializedName ("release_date") var relaseDate : String,
    @SerializedName ("id") var MovieId: Long,
    @SerializedName ("original_title")var originalTitle : String,
    @SerializedName ("overview") var movieDescription : String

    )