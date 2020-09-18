package com.examen.parrot.stores.source

import com.examen.parrot.stores.data.StoreService
import com.examen.parrot.stores.domain.ResponseProducts
import com.examen.parrot.stores.domain.ResponseStore
import com.examen.parrot.utils.BaseDataSource
import com.examen.parrot.utils.Resource
import javax.inject.Inject

class StoreRemoteSource @Inject constructor(private val storeService: StoreService) :
    BaseDataSource() {

    suspend fun getStoresAsync(token: String): Resource<ResponseStore> {
        return getResult { storeService.getStoresAsync(token) }
    }

    suspend fun getProducts(token: String,storeId:String): Resource<ResponseProducts> {
        return getResult { storeService.getProductsAsync(token,storeId) }
    }

}