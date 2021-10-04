package com.example.composesample.data.model.remote

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("id")
    val id: String,
    @SerializedName("base")
    val base: Base,
    @SerializedName("filmography")
    val filmography: List<Filmography>,
)