package com.example.composesample.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
):Parcelable