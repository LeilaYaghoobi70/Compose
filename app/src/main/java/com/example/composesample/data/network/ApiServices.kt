package com.example.composesample.data.network

import com.example.composesample.data.model.remote.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("get-all-filmography")
    suspend fun getCountries(
        @Query("nconst") ncont: String = "nm0001667"
    ): Movie
}