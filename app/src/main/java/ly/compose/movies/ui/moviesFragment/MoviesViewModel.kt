package ly.compose.movies.ui.moviesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ly.compose.movies.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    init {
        viewModelScope.launch {
            repository.getMovies(1)
        }
    }
}