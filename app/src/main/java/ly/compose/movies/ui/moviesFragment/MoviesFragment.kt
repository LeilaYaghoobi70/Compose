package ly.compose.movies.ui.moviesFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment:Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun onCreateView(){

    }
}