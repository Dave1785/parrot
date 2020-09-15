package com.examen.parrot.stores.domain

import com.examen.parrot.stores.framework.Store

data class Result(

    var uuid:String,
    var username:String,
    var stores:List<Store>
)