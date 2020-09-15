package com.examen.parrot.stores.domain

data class Result(

    var uuid:String,
    var username:String,
    var stores:List<Store>
)