package com.examen.parrot.stores.framework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Store::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): StoreDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "characters")
                .fallbackToDestructiveMigration()
                .build()
    }

}