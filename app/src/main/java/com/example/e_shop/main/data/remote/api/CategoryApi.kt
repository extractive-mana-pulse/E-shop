package com.example.e_shop.main.data.remote.api

import com.example.e_shop.main.domain.model.Category
import com.example.e_shop.main.domain.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApi {

    @GET("categories")
    suspend fun getAllCategories(): Response<List<Category>>

    @GET("categories/{categoryID}/products")
    suspend fun getAllProductsByCategory(@Path("categoryID") categoryId: Int) : Response<List<Product>>
}