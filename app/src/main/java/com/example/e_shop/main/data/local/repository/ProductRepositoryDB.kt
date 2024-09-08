package com.example.e_shop.main.data.local.repository

import androidx.lifecycle.LiveData
import com.example.e_shop.main.domain.model.Product

interface ProductRepositoryDB {

    suspend fun insert(product : Product) : Long

    fun getAllProducts(): LiveData<List<Product>>

    suspend fun deleteProduct(product: Product)

    suspend fun isItemInDatabase(itemId: Int): Boolean

    suspend fun getProductsByIds(productIds: List<Int>): List<Product>
}