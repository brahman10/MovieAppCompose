package com.lyscraft.data.apiServices

import com.lyscraft.data.constants.APIConstants
import com.lyscraft.data.entities.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET(APIConstants.MOVIE_LIST)
    suspend fun getMovieList(@Query(APIConstants.API_KEY) apiKey: String): Response<MovieListResponse>
}