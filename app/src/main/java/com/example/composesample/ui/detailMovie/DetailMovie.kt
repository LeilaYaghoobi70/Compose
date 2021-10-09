package com.example.composesample.ui.detailMovie

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.composesample.data.model.remote.DetailMovie

@Composable
fun DetailMovie(
    id: String?,
    category: String?,
    detailMovieViewModel: DetailMovieViewModel,
    modifier: Modifier
) {
    val viewState by detailMovieViewModel.state.collectAsState()

    if (id != null && category != null) {
        detailMovieViewModel.getMovieDetail(id, category)
    }

    HomeDetailView(
        modifier = modifier,
        detailMovie = viewState.successful,
        isLoading = viewState.Loading,
        throwable = viewState.throwable
    )
}

@Composable
fun HomeDetailView(
    modifier: Modifier,
    detailMovie: List<DetailMovie>?,
    isLoading: Boolean?,
    throwable: Throwable?,
) {
    Box(modifier = modifier) {
        if (!detailMovie.isNullOrEmpty())
            DetailMovieView(
                modifier = modifier,
                detailMovie = detailMovie[0]
            )

        if (isLoading == true)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        throwable?.let {
            Toast.makeText(
                LocalContext.current,
                it.message,
                Toast.LENGTH_LONG
            ).show()
        }

    }

}

@Composable
fun DetailMovieView(
    modifier: Modifier,
    detailMovie: DetailMovie,
) {

}