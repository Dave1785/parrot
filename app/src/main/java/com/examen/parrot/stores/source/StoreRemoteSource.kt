package com.examen.parrot.stores.source

import android.util.Log
import com.examen.parrot.stores.data.StoreDataSource
import com.examen.parrot.stores.data.StoreService
import com.examen.parrot.stores.domain.ResponseStore
import javax.inject.Inject

class StoreRemoteSource @Inject constructor(private val storeService: StoreService): StoreDataSource{

    override suspend fun getStoresAsync(token:String): ResponseStore? {
            var responseStore:ResponseStore?=null
            try {
                responseStore= storeService.getStoresAsync(token).await()
            }catch (e:Exception){
                Log.d("Exception","Exception $e")
            }
            return responseStore
    }

}