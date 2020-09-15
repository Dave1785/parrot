package com.examen.parrot.stores.framework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Store::class],version = 1,exportSchema = false)
abstract class StoreDataBase : RoomDatabase() {

    abstract fun parrotDao(): StoreDao

    companion object {

        @Volatile
        private  var INSTANCE: StoreDataBase?=null


        fun getDatabase(context: Context): StoreDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreDataBase::class.java,
                    "store_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}