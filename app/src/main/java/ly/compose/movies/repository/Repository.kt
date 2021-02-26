package ly.compose.movies.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ly.compose.movies.model.Movie
import ly.compose.movies.repository.network.Network
import javax.inject.Inject

class Repository @Inject constructor(
    private val network: Network
){
    suspend fun getMovies(pageNumber: Int) :List<Movie> = withContext(Dispatchers.IO){
        network.getMovies(pageNumber = pageNumber)
    }
}