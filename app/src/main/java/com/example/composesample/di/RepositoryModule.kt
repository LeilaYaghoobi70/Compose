package com.example.composesample.di

import com.example.composesample.data.network.ApiServices
import com.example.composesample.data.repository.MovieRepository
import com.example.composesample.data.repository.MovieRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(apiServices: ApiServices): MovieRepository = MovieRepositoryImp(
        apiServices
    )
}