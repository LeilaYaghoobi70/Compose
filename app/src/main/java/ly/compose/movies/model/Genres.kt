package ly.compose.movies.model

import com.squareup.moshi.Json

data class Genres(
    @Json(name = "id")
    var serverId: Int?,

    @Json(name = "name")
    var name: String?
)