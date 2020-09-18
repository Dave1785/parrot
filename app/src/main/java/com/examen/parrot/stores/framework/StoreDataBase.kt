package com.examen.parrot.stores.framework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StoreEntity::class, Product::class], version = 1, exportSchema = false)
abstract class StoreDataBase : RoomDatabase(){

    abstract fun storeDao(): StoreDao

    abstract fun productDao():ProductDao

    companion object {
        @Volatile private var instance: StoreDataBase? = null

        fun getDatabase(context: Context): StoreDataBase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, StoreDataBase::class.java, "characters")
                .fallbackToDestructiveMigration()
                .build()
    }
}