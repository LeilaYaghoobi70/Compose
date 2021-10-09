package com.example.composesample.data.network

import com.example.composesample.data.model.remote.DetailMovie
import com.example.composesample.data.model.remote.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("get-all-filmography")
    suspend fun getFilmography(
        @Query("nconst") ncont: String = "nm0001667"
    ): Movie

    @GET("get-filmography-appearances")
    suspend fun getFilmographyAppearances(
        @Query("nconst") ncont: String = "nm0001667",
        @Query("tconst") id: String?,
        @Query("region") region: String?,
        @Query("category") category: String?
    ): List<DetailMovie>
}