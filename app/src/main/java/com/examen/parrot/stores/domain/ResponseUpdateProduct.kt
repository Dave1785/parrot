package com.examen.parrot.stores.domain

import com.examen.parrot.stores.framework.Product

data class ResponseUpdateProduct(
    var status: String,
    var result:Product
)