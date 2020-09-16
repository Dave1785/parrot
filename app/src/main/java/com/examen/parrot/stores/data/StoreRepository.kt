package com.examen.parrot.stores.data

import com.examen.parrot.stores.domain.Store
import com.examen.parrot.stores.framework.StoreDao
import com.examen.parrot.stores.framework.StoreEntity
import com.examen.parrot.stores.source.StoreRemoteSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(private val storeSource: StoreRemoteSource, private val storeDao: StoreDao){

     suspend fun getStores(token:String):List<StoreEntity>?{
         var stores=storeSource.getStoresAsync(token)
         if(stores==null){

         }
        val stories=storeDao.getAllStores()
        return stories
    }

}