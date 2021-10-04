package com.example.composesample.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composesample.data.model.Movie


@Composable
fun Movies(
    navController: NavController
) {

    val viewModel: MoviesViewModel = hiltViewModel()

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
    selectMovie: ((Movie) -> Unit)
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
fun CreateMovieList(categories: List<Movie>, selectMovie: ((Movie) -> Unit)) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        val (button, Row) = createRefs()
        categories.forEach { movie ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .constrainAs(Row) {
                    top.linkTo(parent.top, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                }) {
                Text(text = movie.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                        .clickable {
                            selectMovie.invoke(movie)
                        }
                )
            }

        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                }
                .fillMaxWidth()
                .height(48.dp)
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
        ) {
            Text(text = "Add")
        }
    }

}
