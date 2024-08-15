package com.lyscraft.data.di

import com.lyscraft.data.apiServices.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun provideLoginApiService(retrofit: Retrofit): MovieApiService =
        retrofit.create(MovieApiService::class.java)

}