package com.example.e_shop.main.data.remote.repository

import com.example.e_shop.main.domain.model.Category
import com.example.e_shop.main.domain.model.Product
import retrofit2.Response

interface CategoryRepository {

    suspend fun getAllCategories(): Response<List<Category>>

    suspend fun getAllProductsByCategory(categoryId: Int) : Response<List<Product>>
}