package com.example.e_shop.main.domain.repositoryImpl

import com.example.e_shop.main.data.remote.api.HomeApi
import com.example.e_shop.main.data.remote.repository.ProductRepository
import com.example.e_shop.main.domain.model.Product
import retrofit2.Response

class ProductRepositoryImpl(private val homeApi: HomeApi) : ProductRepository {

    override suspend fun getAllProducts(): Response<List<Product>> = homeApi.getAllProducts()

    override suspend fun getProductsWithPagination(): Response<List<Product>> = homeApi.getProductsWithPagination()

}