package com.example.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composesample.ui.movies.Movies
import com.example.composesample.ui.theme.ComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SettingNavController()
                }
            }
        }
    }
}


@Composable
fun SettingNavController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movies") {
        composable("movies") { Movies(navController = navController) }
        /*...*/
    }
}
