package com.lyscraft.data.di

import com.lyscraft.data.repoImpl.MovieListRepoImpl
import com.lyscraft.domain.repo.MovieListRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    @Singleton
    fun provideMovieRepo(movieListRepo: MovieListRepoImpl): MovieListRepo = movieListRepo
}