package com.examen.parrot.stores.data

import com.examen.parrot.stores.framework.StoreDao
import com.examen.parrot.stores.source.StoreRemoteSource
import com.examen.parrot.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(
    private val storeSource: StoreRemoteSource,
    private val storeDao: StoreDao
) {

    suspend fun getStores(token: String) = performGetOperation(
        databaseQuery = { storeDao.getAllStores() },
        networkCall = { storeSource.getStoresAsync(token) },
        saveCallResult = { storeDao.insertAll(it) }
    )


}