package com.example.composesample.di

import com.example.composesample.data.network.ApiServices
import com.example.composesample.data.repository.CountryRepository
import com.example.composesample.data.repository.CountryRepositoryImp
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
    fun provideMovieRepository(apiServices: ApiServices): CountryRepository = CountryRepositoryImp(
        apiServices
    )
}