package com.example.composesample.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesample.model.Movie
import com.example.composesample.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val mMovieRepository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MoviesState(Loading = true)

            mMovieRepository.getMovies()
                .catch { throwable ->
                    _state.value = MoviesState(throwable = throwable)
                }.collect {
                    _state.value = MoviesState(successful = it)
                }
        }
    }


}

data class MoviesState(
    val Loading: Boolean? = false,
    val throwable: Throwable? = null,
    val successful: List<Movie>? = null
)