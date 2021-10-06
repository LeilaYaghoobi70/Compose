package com.example.composesample.data.repository

import com.example.composesample.data.model.remote.Movie
import com.example.composesample.data.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountryRepositoryImp (
    private val mApiServices: ApiServices
        ): CountryRepository {

    override fun getCountry(): Flow<Movie> = flow {
        emit(mApiServices.getCountries())
    }.flowOn(Dispatchers.IO)

    override fun add(Movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun getMovie(id: Int): Flow<Movie> {
        TODO("Not yet implemented")
    }


}