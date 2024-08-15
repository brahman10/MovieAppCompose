package com.lyscraft.domain.repo

import com.lyscraft.base.SealedResult
import com.lyscraft.domain.contents.MovieListContent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Yash Chaturvedi on 14/08/24.
 */
interface MovieListRepo {
    suspend fun getMovieList(): Flow<SealedResult<List<MovieListContent>>>
}