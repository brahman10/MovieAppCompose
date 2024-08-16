package com.lyscraft.apparel.ui.movieDetail

import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lyscraft.apparel.R
import com.lyscraft.apparel.compose.components.LoadNetworkImageWithPlaceholder
import com.lyscraft.apparel.ui.movieList.MovieListViewModel
import com.lyscraft.apparel.ui.movieList.MovieListViewModel.Companion.IMAGE_BASE_URL

/**
 * Created by Yash Chaturvedi on 15/08/24.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Stable
@Composable
fun SharedTransitionScope.MovieDetailScreen(
    navController: NavController,
    id: String,
    viewModel: MovieListViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Log.d("TAG", "MovieDetailScreen: $viewModel")
    val movieListContent = viewModel.movieList.firstOrNull {
        it.id.toString() == id
    }
    /*LaunchedEffect(Unit) {
        movieListContent = viewModel.movieList.firstOrNull {
            it.id.toString() == id
        }
    }*/
    Image(painter = painterResource(id = R.drawable.ic_back_ios),
        contentDescription = "Back",
        modifier = Modifier
            .clickable {
                navController.popBackStack()
            }
            .padding(vertical = 8.dp, horizontal = 8.dp))
    Column(
        modifier = Modifier
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        LoadNetworkImageWithPlaceholder(
            baseUrl = IMAGE_BASE_URL,
            imageUrl = movieListContent?.backdropPath,
            aspectRatio = 1f,
            modifier = Modifier.sharedElement(
                state = rememberSharedContentState(key = "image/${movieListContent?.id}"),
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = { _, _ ->
                    tween(durationMillis = 500)
                }
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = movieListContent?.title ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = movieListContent?.overview ?: "",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )
    }
}