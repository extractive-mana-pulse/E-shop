package com.example.e_shop.main.domain.repositoryImpl

import com.example.e_shop.main.data.remote.api.CategoryApi
import com.example.e_shop.main.data.remote.repository.CategoryRepository
import com.example.e_shop.main.domain.model.Category
import com.example.e_shop.main.domain.model.Product
import retrofit2.Response

class CategoryRepositoryImpl(private val categoryApi: CategoryApi): CategoryRepository {

    override suspend fun getAllCategories(): Response<List<Category>> = categoryApi.getAllCategories()

    override suspend fun getAllProductsByCategory(categoryId: Int): Response<List<Product>> = categoryApi.getAllProductsByCategory(categoryId)

}