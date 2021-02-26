package ly.compose.movies.repository.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import ly.compose.movies.model.Movie
import javax.inject.Inject

class Network @Inject constructor(private val movieApi: MovieAPI){

    suspend fun getMovies(pageNumber: Int): List<Movie> = withContext(Dispatchers.IO){
        movieApi.getMovies(page = pageNumber)
    }

}