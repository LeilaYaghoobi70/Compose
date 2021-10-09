package com.example.composesample.data.repository

import com.example.composesample.data.model.remote.DetailMovie
import com.example.composesample.data.model.remote.Movie
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface MovieRepository {
    fun getFilmography(): Flow<Movie>
    fun getFilmographyAppearances(
        id: String?,
        region: String?,
        category: String?
    ): Flow<List<DetailMovie>>
}