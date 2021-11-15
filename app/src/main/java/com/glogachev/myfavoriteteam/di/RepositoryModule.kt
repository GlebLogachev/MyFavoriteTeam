package com.glogachev.myfavoriteteam.di

import com.glogachev.myfavoriteteam.data.TeamRepositoryImpl
import com.glogachev.myfavoriteteam.domain.TeamRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTeamRepository(dataRepositoryImpl:TeamRepositoryImpl): TeamRepository =
        dataRepositoryImpl
}