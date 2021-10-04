package com.example.composesample.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
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
import com.example.composesample.data.model.remote.Film
import com.example.composesample.data.model.remote.Filmography


@Composable
fun CountryView(
    navController: NavController
) {

    val viewModel: CountryViewModel = hiltViewModel()

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
    film: Film?,
    isLoading: Boolean?,
    throwable: Throwable?,
    selectCountry: ((Filmography) -> Unit)
) {

    Column(modifier = modifier) {
        if (film != null) {
            CreateCountryList(
                films = film,
                selectActor = selectCountry,
                isLoading = isLoading
            )
        }

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
    ) {
        val (button, Row, circularProgress) = createRefs()
        films.filmography.forEach { filmography ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Cyan)
                .constrainAs(Row) {
                    top.linkTo(parent.top, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                }) {
                Text(text = filmography.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                        .clickable {
                            selectActor.invoke(filmography)
                        }
                )
            }

        }

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
