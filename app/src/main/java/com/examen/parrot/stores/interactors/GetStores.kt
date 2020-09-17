package com.examen.parrot.stores.interactors


import android.util.Log
import com.examen.parrot.stores.data.StoreRepository
import com.examen.parrot.stores.framework.StoreEntity
import javax.inject.Inject

class GetStores @Inject constructor(private val storeRepository: StoreRepository) {

     suspend fun getStores(token: String):List<StoreEntity>? {
       return storeRepository.getStores(token)
    }

    suspend fun getStoresRefresh(token: String){
        storeRepository.getStores(token)
    }

}