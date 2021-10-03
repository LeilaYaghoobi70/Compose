package com.example.composesample.repository

import com.example.composesample.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImp : MovieRepository {

    private val movies = ArrayList<Movie>().apply {
        this.add(Movie("text","1234",0))
    }

    override fun getMovies(): Flow<List<Movie>> = flow {
        emit(movies)
    }

    override fun add(movie: Movie) {
        movies.add(movie)
    }

    override fun getMovie(id: Int): Flow<Movie> = flow {
        movies.forEach {
            if (it.id == id) {
                emit(it)
                return@forEach
            }
        }
    }
}