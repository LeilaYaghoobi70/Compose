package ly.compose.movies.repository.network

import ly.compose.movies.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular")
    suspend fun getMovies(@Query ("page") page: Int): List<Movie>
}