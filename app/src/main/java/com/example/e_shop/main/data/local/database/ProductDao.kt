package com.example.e_shop.main.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.e_shop.main.domain.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product : Product) : Long

    @Query("SELECT * FROM product")
    fun getAllProducts(): LiveData<List<Product>>

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT COUNT(*) FROM product WHERE id = :itemId")
    suspend fun isItemInDatabase(itemId: Int): Int

}