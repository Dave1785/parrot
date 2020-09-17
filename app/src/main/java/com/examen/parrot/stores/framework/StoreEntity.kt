package com.examen.parrot.stores.framework

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "stores")
data class StoreEntity(
    @PrimaryKey
    var  uuid : String,

    @NonNull
    @ColumnInfo(name = "name")
    var name: String
)