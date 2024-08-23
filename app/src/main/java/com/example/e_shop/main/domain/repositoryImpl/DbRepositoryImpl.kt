package com.example.e_shop.main.domain.repositoryImpl

import androidx.lifecycle.LiveData
import com.example.e_shop.main.data.local.database.ArticleDatabase
import com.example.e_shop.main.data.local.repository.ProductRepositoryDB
import com.example.e_shop.main.domain.model.Product

class DbRepositoryImpl(private val db : ArticleDatabase) : ProductRepositoryDB {

    override suspend fun insert(product: Product): Long {
        return db.getProductDao().insert(product)
    }

    override fun getAllProducts(): LiveData<List<Product>> {
        return db.getProductDao().getAllProducts()
    }

    override suspend fun deleteProduct(product: Product) {
        return db.getProductDao().deleteProduct(product)
    }

    override suspend fun isItemInDatabase(itemId: Int): Boolean {
        return db.getProductDao().isItemInDatabase(itemId) > 0
    }
}