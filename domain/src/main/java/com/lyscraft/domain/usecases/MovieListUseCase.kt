package com.lyscraft.domain.usecases

import com.lyscraft.domain.repo.MovieListRepo
import javax.inject.Inject

/**
 * Created by Yash Chaturvedi on 14/08/24.
 */
class MovieListUseCase @Inject constructor(private val movieListRepo: MovieListRepo) {

    suspend fun invoke() = movieListRepo.getMovieList()
}