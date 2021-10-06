package com.example.composesample.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesample.data.model.remote.Movie
import com.example.composesample.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val mCountryRepository: CountryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(Country(Loading = true))
    val state: StateFlow<Country> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = Country(Loading = true)
            mCountryRepository.getCountry()
                .catch { throwable ->
                    _state.value = Country(throwable = throwable)
                }.collect {
                    _state.value = Country(successful = it)
                }
        }
    }

}

data class Country(
    val Loading: Boolean? = false,
    val throwable: Throwable? = null,
    val successful: Movie? = null
)