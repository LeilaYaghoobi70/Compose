package com.example.composesample

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.composesample.data.model.remote.Movie
import com.example.composesample.ui.detailMovie.DetailMovie
import com.example.composesample.ui.movies.MovieView

const val MovieView = "MovieView"
const val DetailMovie = "DetailMovie"
const val Movieography = "Movieography"
const val ID = "ID"

@Composable
fun SettingNavController() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieView,
    ) {
        composable(
            route = MovieView
        ) { MovieView(navController = navController) }
        composable(
            route = "$DetailMovie{/$ID}",
            arguments = listOf(navArgument(ID) {
                defaultValue = null
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry -> DetailMovie(backStackEntry.arguments?.getString(ID)) }
    }
}
