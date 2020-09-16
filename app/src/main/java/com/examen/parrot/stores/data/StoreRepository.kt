package com.examen.parrot.stores.data

import com.examen.parrot.stores.domain.Store
import com.examen.parrot.stores.framework.StoreDao
import com.examen.parrot.stores.framework.StoreEntity
import com.examen.parrot.stores.source.StoreRemoteSource
import com.examen.parrot.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(private val storeSource: StoreRemoteSource, private val storeDao: StoreDao){



/*    var stores=storeSource.getStoresAsync(token)
    if(stores==null){
        return storeDao.getAllStores()
    }else{
        storeDao.insertAll(stores.result.stores)
    }
    val stories=storeDao.getAllStores()
    return stories*/

}