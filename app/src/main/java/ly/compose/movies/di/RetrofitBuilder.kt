package ly.compose.movies.di

import com.squareup.moshi.Moshi

import ly.compose.movies.BuildConfig
import ly.compose.movies.repository.network.MovieAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit



object RetrofitBuilder{

    private val loggingInterceptor = HttpLoggingInterceptor()

    init {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

   private val interceptor = Interceptor { chain ->
        val newRequest = chain
            .request().apply {
                newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()

                url
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)

            }

        chain.proceed(newRequest)

    }

    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()


    private fun provideRetrofit(moshi: Moshi) = Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    fun services(moshi: Moshi) :MovieAPI =
        provideRetrofit(moshi = moshi).create(MovieAPI::class.java)

}
