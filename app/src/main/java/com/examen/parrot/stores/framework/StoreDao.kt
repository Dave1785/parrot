package com.examen.parrot.stores.framework

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addStore(store:Store )

    @Update
    fun updateStore(store: Store)

    @Query("SELECT * from stores_table LIMIT 1")
    fun getFirstStore():List<Store>

    @Delete
    fun deleteStore(song: Store)

    @Query("DELETE FROM stores_table")
    fun deleteAll()

    @Query("SELECT * from stores_table ORDER BY title ASC")
    fun getAllStores(): List<Store>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stores: List<Store>)

}