package com.lyscraft.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lyscraft.data.BuildConfig
import com.lyscraft.data.utils.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckInterceptor: ChuckerInterceptor,
        networkInterceptor: NetworkInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(2, TimeUnit.MINUTES) // write timeout
            .readTimeout(2, TimeUnit.MINUTES)
            .pingInterval(3, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .addInterceptor(httpLoggingInterceptor)
        if (BuildConfig.DEBUG)
            clientBuilder.addInterceptor(chuckInterceptor)
        return clientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setStrictness(Strictness.LENIENT)
            .create()
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .maxContentLength(250_000L)
            .alwaysReadResponseBody(true)
            .createShortcut(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun provideNetworkInterceptor(context: Context): NetworkInterceptor =
        NetworkInterceptor(context)

    @Provides
    @Singleton
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}