package com.examen.parrot.stores.data

import com.examen.parrot.stores.domain.ResponseStore

interface StoreDataSource {

    suspend fun getStores(token:String):ResponseStore

}