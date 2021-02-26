package ly.compose.movies.model

import androidx.room.TypeConverter

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class GenreTypeConverter {

    private val moshi = Moshi.Builder().build()
    private val type = Types.newParameterizedType(MutableList::class.java, Genres::class.java)
    private var adapter = moshi.adapter<List<Genres>>(type)

    @TypeConverter
    fun toJson( genres: List<Genres>) : String = adapter.toJson(genres)

    @TypeConverter
    fun toModel(genres: String): List<Genres> = adapter.fromJson(genres)!!

}