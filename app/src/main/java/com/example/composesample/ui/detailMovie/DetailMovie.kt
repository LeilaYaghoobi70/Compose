package com.example.composesample.ui.detailMovie

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.composesample.data.model.remote.DetailMovie
import com.example.composesample.R

@Composable
fun DetailMovie(
    id: String?,
    category: String?,
    detailMovieViewModel: DetailMovieViewModel,
    modifier: Modifier
) {
    val viewState by detailMovieViewModel.state.collectAsState()

    when (viewState) {
        is DetailMovieStatus.Successful -> HomeDetailView(
            modifier = modifier,
            detailMovie = (viewState as DetailMovieStatus.Successful).successful,
            isLoading = false,
            throwable = null
        )
        is DetailMovieStatus.Throwable -> HomeDetailView(
            modifier = modifier,
            detailMovie = null,
            isLoading = false,
            throwable = (viewState as DetailMovieStatus.Throwable).throwable,
        )
        is DetailMovieStatus.Loading -> HomeDetailView(
            modifier = modifier,
            detailMovie = null,
            isLoading = true,
            throwable = null
        )
        DetailMovieStatus.None -> {
            if (id != null && category != null)
                detailMovieViewModel.getMovieDetail(id, category)
        }
    }

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

    Column(modifier = modifier) {
        Image(
            painter = rememberImagePainter(detailMovie.mImage?.url), contentDescription = "Actor",
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(5.dp))
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = detailMovie.mTitle,
            color = colorResource(id = R.color.textColorTitle),
            modifier = Modifier.padding(12.dp)
        )
        Text(
            text = detailMovie.mTitleType,
            color = colorResource(id = R.color.textColorTitle),
            modifier = Modifier.padding(12.dp)
        )
        Text(
            text = stringResource(id = R.string.startYear).plus(
                detailMovie.mStartYear ?: detailMovie.mSeriesStartYear
            ),
            color = colorResource(id = R.color.textColorTitle),
            modifier = Modifier.padding(12.dp)
        )
        Text(
            text = stringResource(id = R.string.endYear).plus(
                detailMovie.mEndYear ?: detailMovie.mSeriesEndYear
            ),
            color = colorResource(id = R.color.textColorTitle),
            modifier = Modifier.padding(12.dp)
        )

    }

}