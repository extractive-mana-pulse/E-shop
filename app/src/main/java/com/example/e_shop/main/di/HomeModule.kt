package com.example.e_shop.main.di

import com.example.e_shop.main.data.remote.api.CategoryApi
import com.example.e_shop.main.data.remote.repository.CategoryRepository
import com.example.e_shop.main.domain.repositoryImpl.CategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit) : CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(categoryApi: CategoryApi): CategoryRepository{
        return CategoryRepositoryImpl(categoryApi)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}