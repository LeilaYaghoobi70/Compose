package com.example.composesample.di

import com.example.composesample.repository.MovieRepository
import com.example.composesample.repository.MovieRepositoryImp
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
    fun provideMovieRepository(): MovieRepository = MovieRepositoryImp()
}