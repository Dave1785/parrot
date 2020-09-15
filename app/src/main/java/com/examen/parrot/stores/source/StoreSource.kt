package com.examen.parrot.stores.source

import com.examen.parrot.stores.data.StoreDataSource
import com.examen.parrot.stores.data.StoreService
import com.examen.parrot.stores.domain.ResponseStore
import javax.inject.Inject

class StoreSource @Inject constructor(private val storeService: StoreService): StoreDataSource{

    override suspend fun getStores(token:String): ResponseStore {
       return storeService.getStores(token)
    }

}