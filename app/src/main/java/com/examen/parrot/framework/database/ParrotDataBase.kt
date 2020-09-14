package com.examen.parrot.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.examen.parrot.framework.database.dao.ParrotDao
import com.examen.parrot.framework.database.entitys.ParrotEntity


@Database(entities = [ParrotEntity::class],version = 1,exportSchema = false)
abstract class ParrotDataBase : RoomDatabase() {

    abstract fun dao(): ParrotDao


    companion object {

        @Volatile
        private  var INSTANCE: ParrotDataBase?=null



        fun getDatabase(context: Context): ParrotDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParrotDataBase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}