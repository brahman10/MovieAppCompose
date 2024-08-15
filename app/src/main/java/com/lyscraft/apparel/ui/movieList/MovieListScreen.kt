package com.lyscraft.apparel.ui.movieList

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lyscraft.apparel.compose.components.LoadNetworkImageWithPlaceholder
import com.lyscraft.apparel.compose.components.PageLoader
import com.lyscraft.apparel.compose.components.SearchBar
import com.lyscraft.apparel.compose.components.StateScreen
import com.lyscraft.apparel.enums.ViewState
import com.lyscraft.apparel.navigation.routes.Routes
import com.lyscraft.apparel.ui.movieList.MovieListViewModel.Companion.IMAGE_BASE_URL
import com.lyscraft.base.SealedResult
import com.lyscraft.domain.contents.MovieListContent
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Yash Chaturvedi on 15/08/24.
 */
@Stable
@Composable
fun MovieListScreen(
    navController: NavController, viewModel: MovieListViewModel
) {
    val movieList = viewModel.filterMovieListState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var searchJob: Job? by remember { mutableStateOf(null) }
    val context = LocalContext.current as ComponentActivity
    BackHandler {
        context.finish()
    }
    LaunchedEffect(Unit) {
        viewModel.getMoviesList()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        when (movieList.value) {
            is SealedResult.Error -> {
                StateScreen(modifier = Modifier.fillMaxSize(), ViewState.ERROR)
            }

            SealedResult.Loading -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(10) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            PageLoader()
                        }
                    }

                }
            }

            is SealedResult.Success -> {
                SearchBar(searchQuery) {
                    // Cancel any previous job that was running
                    searchQuery = it
                    searchJob?.cancel()
                    searchJob = coroutineScope.launch {
                        delay(1000)
                        viewModel.searchMovies(it)
                    }
                }
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    if (movieList.value is SealedResult.Success) {
                        val data = (movieList.value as SealedResult.Success).data
                        viewModel.movieList = data as ArrayList
                        items(data) {
                            MovieListItem(modifier = Modifier.clickable {
                                Log.d("TAG", "MovieDetailScreen: $viewModel")
                                navController.navigate("${Routes.MOVIE_DETAIL}/${it.id}")
                            }, movieListContent = it)
                        }
                    }

                }
            }

            SealedResult.None -> {

            }
        }

    }
}

@Stable
@Composable
fun MovieListItem(modifier: Modifier, movieListContent: MovieListContent) {
    Column(modifier = modifier.padding(8.dp)) {
        LoadNetworkImageWithPlaceholder(
            baseUrl = IMAGE_BASE_URL, imageUrl = movieListContent.posterPath
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movieListContent.title ?: "",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.height(24.dp))
    }

}