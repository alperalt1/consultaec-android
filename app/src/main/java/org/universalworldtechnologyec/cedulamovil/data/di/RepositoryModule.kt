package org.universalworldtechnologyec.cedulamovil.data.di

import org.universalworldtechnologyec.cedulamovil.data.repository.AuthRepositoryImp
import org.universalworldtechnologyec.cedulamovil.data.repository.HomeRepositoryImp
import org.universalworldtechnologyec.cedulamovil.domain.repository.AuthRepository
import org.universalworldtechnologyec.cedulamovil.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providesAuthRepository(
        authRepositoryImp: AuthRepositoryImp
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun providesHomeRepository(
        homeRepositoryImp: HomeRepositoryImp
    ): HomeRepository

}