package com.example.composesample.repository

import com.example.composesample.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    fun add(movie: Movie)
    fun getMovie(id: Int): Flow<Movie>
}