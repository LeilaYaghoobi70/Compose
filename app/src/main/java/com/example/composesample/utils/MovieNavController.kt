package com.example.composesample.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.composesample.R
import com.example.composesample.ui.detailMovie.DetailMovie
import com.example.composesample.ui.movies.MovieView

const val MovieView = "MovieView"
const val DetailMovie = "DetailMovie"
const val ID = "ID"
const val Category = "Category"

@Composable
fun SettingNavController() {
    val navController = rememberNavController()
    val modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.background))
    NavHost(
        navController = navController,
        startDestination = MovieView,
    ) {
        composable(
            route = MovieView
        ) {
            MovieView(
                navController = navController,
                modifier = modifier
            )
        }
        composable(
            route = DetailMovie.plus("/{$ID}/{$Category}"),
            arguments = listOf(navArgument(ID) {
                defaultValue = null
                type = NavType.StringType
                nullable = true
            }, navArgument(Category) {
                defaultValue = null
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            DetailMovie(
                id = backStackEntry.arguments?.getString(ID),
                category = backStackEntry.arguments?.getString(Category),
                detailMovieViewModel = hiltViewModel(),
                modifier = modifier
            )
        }
    }
}
