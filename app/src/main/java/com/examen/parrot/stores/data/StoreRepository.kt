package com.examen.parrot.stores.data

import com.examen.parrot.stores.framework.Store
import com.examen.parrot.stores.framework.StoreDao
import com.examen.parrot.stores.source.StoreRemoteSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(private val storeSource: StoreRemoteSource){

    suspend fun getStores(token:String):List<Store>?{
        var response: List<Store>?

        response= storeSource.getStoresAsync(token)?.result?.stores


       return response
    }

}