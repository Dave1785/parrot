package com.examen.parrot.stores.framework

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(

   @PrimaryKey
    var uuid: String="",
    var name: String="",
    var description: String="",
    var imageUrl: String="",
    var legacyId: String="",
    var price: String="",
    var alcoholCount: Int=0,
    var soldAlone: Boolean=false,
    var availability: String=""

)