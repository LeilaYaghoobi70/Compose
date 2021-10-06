package com.example.composesample.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: String,
    @SerializedName("base")
    val base: Base,
    @SerializedName("filmography")
    val movieoGraphy: List<Movieography>,
) : Parcelable