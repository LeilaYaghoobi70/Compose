package com.example.composesample.data.repository

import com.example.composesample.data.model.remote.Film
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountry(): Flow<Film>
    fun add(Film: Film)
    fun getMovie(id: Int): Flow<Film>
}