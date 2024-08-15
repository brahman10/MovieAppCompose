package com.lyscraft.logging.di

import android.content.Context
import com.lyscraft.logging.crashLogging.CrashLogger
import com.lyscraft.logging.eventLogging.EventLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LogModule {
    @Provides
    @Singleton
    fun provideCrashLogger(context: Context): CrashLogger = CrashLogger(context)

    @Provides
    @Singleton
    fun provideEventLogger(context: Context): EventLogger = EventLogger(context)
}