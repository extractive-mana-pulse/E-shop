package com.example.e_shop.auth.di

import com.example.e_shop.auth.data.remote.api.AuthApi
import com.example.e_shop.auth.data.remote.repository.AuthRepository
import com.example.e_shop.auth.domain.repositoryImpl.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi{
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi) : AuthRepository {
        return AuthRepositoryImpl(authApi)
    }
}