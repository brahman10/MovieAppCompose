package com.lyscraft.apparel.ui.movieList

import androidx.lifecycle.viewModelScope
import com.lyscraft.base.BaseViewModel
import com.lyscraft.base.SealedResult
import com.lyscraft.domain.contents.MovieListContent
import com.lyscraft.domain.usecases.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Yash Chaturvedi on 15/08/24.
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: MovieListUseCase
) : BaseViewModel() {
    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"
    }

    private val _movieListState: MutableStateFlow<SealedResult<List<MovieListContent>>> =
        MutableStateFlow(SealedResult.None)
    var movieList = arrayListOf<MovieListContent>()

    private val _filterMovieListState: MutableStateFlow<SealedResult<List<MovieListContent>>> =
        MutableStateFlow(SealedResult.None)
    val filterMovieListState = _filterMovieListState.asStateFlow()

    fun getMoviesList() {
        viewModelScope.launch {
            useCase.invoke().collectLatest {
                _movieListState.emit(it)
                searchMovies("")
            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            if (query.isEmpty() || query.length < 3) {
                _filterMovieListState.emit(_movieListState.value)
            } else {
                _filterMovieListState.emit(SealedResult.Loading)
                val movies = (_movieListState.value as SealedResult.Success).data
                val filteredMovies = movies.filter { it.title?.contains(query, true) == true }
                _filterMovieListState.emit(SealedResult.Success(filteredMovies))
            }

        }

    }

}