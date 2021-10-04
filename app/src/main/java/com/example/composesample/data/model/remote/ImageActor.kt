package com.example.composesample.data.model.remote

import com.google.gson.annotations.SerializedName

data class ImageActor(
    @SerializedName("id")
    val id: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int,
    @SerializedName("url")
    val url: String,
    )