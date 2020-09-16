package com.examen.parrot.stores.domain

data class Store(
    var  uuid : String,
    var name: String,
    var availabilityState: String,
    var providers: List<Any>,
    var config:Any?
)