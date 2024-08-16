package com.example.e_shop.main.data.remote.api

import com.example.e_shop.main.domain.model.Category
import com.example.e_shop.main.domain.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("products")
    suspend fun getAllProducts(): Response<List<Product>>
}