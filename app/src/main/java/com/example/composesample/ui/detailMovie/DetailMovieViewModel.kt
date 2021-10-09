package com.example.composesample.ui.detailMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesample.data.model.remote.DetailMovie
import com.example.composesample.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

const val region = "US"

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailMovieStatus())
    val state: StateFlow<DetailMovieStatus> = _state

    fun getMovieDetail(id: String, category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = DetailMovieStatus(Loading = true)
            movieRepository.getFilmographyAppearances(
                id = id, category = category, region = region
            ).catch {
                _state.value = DetailMovieStatus(throwable = it)
            }.collect {
                _state.value = DetailMovieStatus(successful = it)
            }
        }

    }


}

data class DetailMovieStatus(
    val Loading: Boolean? = false,
    val throwable: Throwable? = null,
    val successful: List<DetailMovie>? = null
)