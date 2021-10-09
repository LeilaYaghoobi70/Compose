package com.example.composesample.ui.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.res.colorResource
import com.example.composesample.R
import com.example.composesample.ui.theme.ComposeSampleTheme
import com.example.composesample.utils.SettingNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                Surface(color = colorResource(id = R.color.background)) {
                    SettingNavController()
                }
            }
        }
    }
}


