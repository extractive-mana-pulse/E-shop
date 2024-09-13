package com.example.e_shop.main.di

import android.app.Application
import androidx.room.Room
import com.example.e_shop.main.data.local.database.ArticleDatabase
import com.example.e_shop.main.data.local.repository.ProductRepositoryDB
import com.example.e_shop.main.data.remote.api.CategoryApi
import com.example.e_shop.main.data.remote.api.HomeApi
import com.example.e_shop.main.data.remote.repository.CategoryRepository
import com.example.e_shop.main.data.remote.repository.ProductRepository
import com.example.e_shop.main.domain.repositoryImpl.CategoryRepositoryImpl
import com.example.e_shop.main.domain.repositoryImpl.DbRepositoryImpl
import com.example.e_shop.main.domain.repositoryImpl.ProductRepositoryImpl
import com.example.e_shop.profile.data.repository.AddressRepository
import com.example.e_shop.profile.domain.repositoryImpl.AddressRepositoryImpl
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
    fun provideCategoryApi(retrofit: Retrofit) : CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit) : HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryApi: CategoryApi): CategoryRepository{
        return CategoryRepositoryImpl(categoryApi)
    }

    @Provides
    @Singleton
    fun provideProductRepository(homeApi: HomeApi): ProductRepository{
        return ProductRepositoryImpl(homeApi)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideDatabase(app : Application) : ArticleDatabase {
        return Room.databaseBuilder(
            app,
            ArticleDatabase::class.java,
            "article.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDbRepository(db: ArticleDatabase): ProductRepositoryDB {
        return DbRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideAddressRepository(db: ArticleDatabase): AddressRepository {
        return AddressRepositoryImpl(db)
    }
}