package com.examen.parrot.stores.framework

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(store: List<Product>?)

    @Query("SELECT * FROM products")
    fun getAllProducts() : List<Product>

}