package com.lyscraft.apparel.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.lyscraft.apparel.compose.theme.LyscraftTheme
import com.lyscraft.apparel.navigation.AppNavigation
import com.lyscraft.domain.usecases.MovieListUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var movieListUseCase: MovieListUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            LyscraftTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}