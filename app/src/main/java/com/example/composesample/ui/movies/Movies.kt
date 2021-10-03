package com.example.composesample.ui.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composesample.model.Movie



@Composable
fun Movies(
    navController: NavController
) {

    val viewModel : MoviesViewModel = hiltViewModel()

    val viewState by viewModel.state.collectAsState()

    Surface(Modifier.fillMaxSize()) {
        HomeContent(
            modifier = Modifier.fillMaxSize(),
            viewState.successful,
            viewState.Loading,
            viewState.throwable
        ) {

        }
    }

}


@Composable
fun HomeContent(
    modifier: Modifier,
    movies: List<Movie>?,
    isLoading: Boolean?,
    throwable: Throwable?,
    selectMovie:((Movie) -> Unit)
) {

    Column(modifier = modifier) {
        if (movies?.isNotEmpty() == true) {
            CreateMovieList(
                categories = movies,
                selectMovie = selectMovie,
            )
        }

    }

}

@Composable
fun CreateMovieList(categories:List<Movie>, selectMovie:((Movie) -> Unit)){
    categories.forEach {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = it.name)
        }
    }

}
