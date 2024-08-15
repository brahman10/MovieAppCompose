package com.lyscraft.data.repoImpl

import com.lyscraft.base.SealedResult
import com.lyscraft.data.BuildConfig
import com.lyscraft.data.apiServices.MovieApiService
import com.lyscraft.data.mappers.MovieListEntityMapper
import com.lyscraft.data.utils.GetNetworkResult
import com.lyscraft.domain.contents.MovieListContent
import com.lyscraft.domain.repo.MovieListRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Yash Chaturvedi on 14/08/24.
 */
class MovieListRepoImpl @Inject constructor(
    private val getNetworkResult: GetNetworkResult,
    private val apiService: MovieApiService,
    private val mapper: MovieListEntityMapper
) : MovieListRepo {
    override suspend fun getMovieList(): Flow<SealedResult<List<MovieListContent>>> {
//        val apiResponse =
        val result = getNetworkResult.getApiResult { apiService.getMovieList(BuildConfig.API_KEY) }
        return result.map {
            when (it) {
                is SealedResult.Success -> SealedResult.Success(it.data.results.map { entity ->
                    mapper.mapData(entity)
                })

                is SealedResult.Error -> SealedResult.Error(it.message, it.code)
                is SealedResult.Loading -> SealedResult.Loading
                is SealedResult.None -> SealedResult.None
            }
        }

    }
}