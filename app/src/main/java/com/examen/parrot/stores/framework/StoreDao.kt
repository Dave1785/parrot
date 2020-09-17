package com.examen.parrot.stores.framework

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StoreDao {

    @Insert
    fun addStore(store: StoreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(store: List<StoreEntity>?)

    @Query("SELECT * FROM stores")
    fun getAllStores() : List<StoreEntity>
}