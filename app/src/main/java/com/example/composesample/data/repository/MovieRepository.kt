package com.example.composesample.data.repository

import com.example.composesample.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    fun add(movie: Movie)
    fun getMovie(id: Int): Flow<Movie>
}