package com.example.composesample.data.model.remote

import com.google.gson.annotations.SerializedName

data class Base(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("legacyNameText")
    val legacyNameText: String,
    @SerializedName("akas")
    val akas: List<String>,
    @SerializedName("image")
    val image:ImageActor?
)