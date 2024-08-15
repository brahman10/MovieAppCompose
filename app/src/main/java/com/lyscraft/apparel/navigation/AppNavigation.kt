package com.lyscraft.apparel.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lyscraft.apparel.navigation.routes.Routes
import com.lyscraft.apparel.ui.movieDetail.MovieDetailScreen
import com.lyscraft.apparel.ui.movieList.MovieListScreen
import com.lyscraft.apparel.ui.movieList.MovieListViewModel
import com.lyscraft.apparel.utils.ConstantsHelper.MOVIE_ID

@Composable
fun AppNavigation(navController: NavHostController) {
    val startDestination = Routes.MOVIE_LIST
    val viewModel: MovieListViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.MOVIE_LIST) {
            MovieListScreen(navController = navController, viewModel)
        }
        composable(
            "${Routes.MOVIE_DETAIL}/{${MOVIE_ID}}",
            arguments = listOf(navArgument(MOVIE_ID) { type = NavType.IntType })
        ) {
            val movieId = it.arguments?.getInt(MOVIE_ID)
            movieId?.let { MovieDetailScreen(navController, movieId.toString(), viewModel) }
        }

    }
}