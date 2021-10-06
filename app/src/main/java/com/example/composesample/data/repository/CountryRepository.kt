package com.example.composesample.data.repository

import com.example.composesample.data.model.remote.Movie
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountry(): Flow<Movie>
    fun add(Movie: Movie)
    fun getMovie(id: Int): Flow<Movie>
}