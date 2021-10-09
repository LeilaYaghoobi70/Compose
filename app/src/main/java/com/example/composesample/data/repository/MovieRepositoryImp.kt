package com.example.composesample.data.repository

import com.example.composesample.data.model.remote.DetailMovie
import com.example.composesample.data.model.remote.Movie
import com.example.composesample.data.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImp(
    private val mApiServices: ApiServices
) : MovieRepository {

    override fun getFilmography(): Flow<Movie> = flow {
        emit(mApiServices.getFilmography())
    }.flowOn(Dispatchers.IO)

    override fun getFilmographyAppearances(
        id: String?,
        region: String?,
        category: String?
    ): Flow<List<DetailMovie>> = flow {
        val result = mApiServices.getFilmographyAppearances(id = id, region = region, category = category)
        emit(result)
    }


}