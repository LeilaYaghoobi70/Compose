package ly.compose.movies.model

import androidx.room.Entity
import com.squareup.moshi.Json


@Entity(tableName = "movieEntity")
data class Movie(

    @Json(name = "vote_count")
    var voteCount: Int?,

    @Json(name = "id")
    var serverId: Int?,

    @Json(name = "video")
    var video: Boolean?,

    @Json(name = "vote_average")
    var voteAverage: Double?,

    @Json(name = "title")
    var title: String?,

    @Json(name = "popularity")
    var popularity: Double?,

    @Json(name = "poster_path")
    var posterPath: String?,

    @Json(name = "original_language")
    var originalLanguage: String?,

    @Json(name = "original_title")
    var originalTitle: String?,

    @Json(name = "backdrop_path")
    var backdropPath: String?,

    @Json(name = "adult")
    var adult: Boolean?,

    @Json(name = "overview")
    var overview: String?,

    @Json(name = "release_date")
    var releaseDate: String?,

    @Json(name = "genres")
    var genreIds: List<Genres>?

)


