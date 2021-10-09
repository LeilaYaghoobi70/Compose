package com.example.composesample.data.model.remote

import android.media.Image
import com.google.gson.annotations.SerializedName

data class DetailMovie(
    @SerializedName("episodeAppearances")
    val mEpisodeAppearances: String?,
    @SerializedName("category")
    val mCategory: String?,
    @SerializedName("characters")
    val mCharacters: List<String>?,
    @SerializedName("endYear")
    val mEndYear: Int?,
    @SerializedName("episodeCount")
    val mEpisodeCount: Int?,
    @SerializedName("startYear")
    val mStartYear: Int?,
    @SerializedName("id")
    val mId: String,
    @SerializedName("title")
    val mTitle: String,
    @SerializedName("seriesEndYear")
    val mSeriesEndYear: String,
    @SerializedName("seriesStartYear")
    val mSeriesStartYear: String,
    @SerializedName("titleType")
    val mTitleType: String,
    @SerializedName("image")
    val mImage: ImageActor?
)
