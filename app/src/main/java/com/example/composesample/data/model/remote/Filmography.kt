package com.example.composesample.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movieography(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: ImageActor?,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("titleType")
    val titleType: String,
) : Parcelable