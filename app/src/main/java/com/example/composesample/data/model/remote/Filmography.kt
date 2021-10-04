package com.example.composesample.data.model.remote

import com.google.gson.annotations.SerializedName

data class Filmography(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: ImageActor,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("titleType")
    val titleType: String,
)