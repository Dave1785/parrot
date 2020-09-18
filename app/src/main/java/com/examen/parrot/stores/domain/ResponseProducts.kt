package com.examen.parrot.stores.domain

import com.examen.parrot.stores.framework.Product

data class ResponseProducts(
    var status:String,
    var results:MutableList<Product>
)