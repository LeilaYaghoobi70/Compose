package com.example.composesample.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.composesample.R
import com.example.composesample.data.model.remote.Base
import com.example.composesample.data.model.remote.Film
import com.example.composesample.data.model.remote.Filmography
import com.example.composesample.data.model.remote.ImageActor
import kotlin.math.round


@Composable
fun CountryView(
    navController: NavController
) {

    val viewModel: CountryViewModel = hiltViewModel()
    val viewState by viewModel.state.collectAsState()

    Surface(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background))
    ) {
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
    film: Film?,
    isLoading: Boolean?,
    throwable: Throwable?,
    selectCountry: ((Filmography) -> Unit)
) {
    if (film != null) {
        CreateCountryList(
            films = film,
            selectActor = selectCountry,
            isLoading = isLoading
        )
    }


}

@Composable
fun CreateCountryList(
    isLoading: Boolean?,
    films: Film,
    selectActor: ((Filmography) -> Unit)
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(R.color.background))
    ) {
        val (titleText, circularProgress, imageViewActor, descriptionText) = createRefs()
        FilmList(films, titleText, imageViewActor, descriptionText)
        if (isLoading == true)
            CircularProgressIndicator(
                progress = 0.5f,
                modifier = Modifier
                    .constrainAs(circularProgress) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .background(Color.Green)
            )
    }


}

@Composable
private fun FilmList(
    films: Film,
    titleText: ConstrainedLayoutReference,
    imageViewActor: ConstrainedLayoutReference,
    descriptionText: ConstrainedLayoutReference,
) {
    LazyColumn(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
    ) {
        films.filmography.forEach { filmography ->
            item {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)

                ) {
                    Text(
                        modifier = Modifier
                            .padding(12.dp)
                            .constrainAs(titleText) {
                                top.linkTo(imageViewActor.top)
                                end.linkTo(parent.end)
                            },
                        text = filmography.title,
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
                                filmography.category +
                                stringResource(id = R.string.space) +
                                stringResource(id = R.string.title) +
                                filmography.titleType +
                                stringResource(id = R.string.space) +
                                stringResource(id = R.string.status) +
                                filmography.status,

                        color = colorResource(id = R.color.textColorDescription),
                        fontSize = 10.sp
                    )


                    filmography.image?.let { rememberImagePainter(data = it.url) }?.let {
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

@Preview
@Composable
fun CountryPreview() {
    CreateCountryList(
        true,
        Film(
            "1",
            Base("", "", "", arrayListOf(), ImageActor("", 0, 0, "")), listOf()
        )
    ) {}
}
