package com.examen.parrot.stores.framework

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "stores")
data class StoreEntity(
    @PrimaryKey
    var  uuid : String
)