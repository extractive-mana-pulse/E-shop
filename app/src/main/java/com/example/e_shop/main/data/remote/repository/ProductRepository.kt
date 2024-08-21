package com.example.e_shop.main.data.remote.repository

import com.example.e_shop.main.domain.model.Product
import retrofit2.Response

interface ProductRepository {

    suspend fun getAllProducts(): Response<List<Product>>

    suspend fun getProductsWithPagination(): Response<List<Product>>
}