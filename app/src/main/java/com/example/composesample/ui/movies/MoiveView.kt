package com.example.composesample.ui.movies

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Surface
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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.composesample.R
import com.example.composesample.data.model.remote.Movie
import com.example.composesample.data.model.remote.Movieography
import com.example.composesample.utils.DetailMovie


@Composable
fun MovieView(
    navController: NavController,
    modifier: Modifier
) {

    val viewModel: MovieViewModel = hiltViewModel()
    val viewState by viewModel.state.collectAsState()

    Surface(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background))
    ) {
        HomeContent(
            modifier = modifier,
            viewState.successful,
            viewState.Loading,
            viewState.throwable
        ) { movieGraphy ->
            navController.navigate(
                DetailMovie.plus(
                    "/${
                        movieGraphy.id
                            .replace("/title/", "")
                            .replace("/", "")
                    }/${movieGraphy.category}"
                )
            )
        }
    }

}


@Composable
fun HomeContent(
    modifier: Modifier,
    Movie: Movie?,
    isLoading: Boolean?,
    throwable: Throwable?,
    selectCountry: ((Movieography) -> Unit)
) {
    Box(modifier = modifier) {
        if (Movie != null) {
            CreateCountryList(
                Movies = Movie,
                selectActor = selectCountry,
            )
        }

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
fun CreateCountryList(
    Movies: Movie,
    selectActor: ((Movieography) -> Unit)
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(R.color.background))
    ) {
        val (titleText, circularProgress, imageViewActor, descriptionText) = createRefs()
        MovieList(Movies, titleText, imageViewActor, descriptionText, selectActor)

    }


}

@Composable
private fun MovieList(
    Movies: Movie,
    titleText: ConstrainedLayoutReference,
    imageViewActor: ConstrainedLayoutReference,
    descriptionText: ConstrainedLayoutReference,
    selectActor: ((Movieography) -> Unit)
) {
    LazyColumn(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
    ) {
        Movies.movieoGraphy.forEach { Movieography ->
            item {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clickable {
                            selectActor.invoke(Movieography)
                        }

                ) {
                    Text(
                        modifier = Modifier
                            .padding(12.dp)
                            .constrainAs(titleText) {
                                top.linkTo(imageViewActor.top)
                                end.linkTo(parent.end)
                            },
                        text = Movieography.title,
                        color = colorResource(id = R.color.textColorTitle),
                        fontSize = 12.sp
                    )

                    Text(
                        modifier = Modifier
                            .padding(12.dp)
                            .constrainAs(descriptionText) {
                                top.linkTo(titleText.bottom)
                                end.linkTo(parent.end)
                            },
                        text = stringResource(id = R.string.category) +
                                Movieography.category +
                                stringResource(id = R.string.space) +
                                stringResource(id = R.string.title) +
                                Movieography.titleType +
                                stringResource(id = R.string.space) +
                                stringResource(id = R.string.status) +
                                Movieography.status,

                        color = colorResource(id = R.color.textColorDescription),
                        fontSize = 10.sp
                    )


                    Movieography.image?.let { rememberImagePainter(data = it.url) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Forest Image",
                            modifier = Modifier
                                .padding(10.dp)
                                .height(60.dp)
                                .width(60.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .constrainAs(imageViewActor) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                },
                            contentScale = ContentScale.Crop
                        )
                    }

                }
                Divider(
                    color = colorResource(id = R.color.dividerColor),
                    thickness = 1.dp,
                    modifier = Modifier.padding(
                        start = 12.dp, end = 12.dp
                    )
                )

            }
        }
    }
}

