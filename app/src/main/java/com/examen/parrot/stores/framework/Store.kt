package com.examen.parrot.stores.framework

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stores_table")
data class Store(

    @PrimaryKey(autoGenerate = true)
    var  uuid : String,

    @NonNull
    @ColumnInfo(name = "title")
    var name: String,

    @NonNull
    @ColumnInfo(name = "title")
    var availabilityState: String

)