package com.andhiratobing.nbr.di

import com.andhiratobing.nbr.data.local.dao.UserDao
import com.andhiratobing.nbr.data.local.mapper.LocalMapper
import com.andhiratobing.nbr.data.remote.api.ApiService
import com.andhiratobing.nbr.data.remote.mapper.RemoteMapper
import com.andhiratobing.nbr.data.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        apiService: ApiService,
        userDao: UserDao,
        remoteMapper: RemoteMapper,
        localMapper: LocalMapper
    ): MainRepository {
        return MainRepository(apiService, userDao, remoteMapper, localMapper)
    }
}