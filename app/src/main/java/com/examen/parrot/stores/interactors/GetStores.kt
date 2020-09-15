package com.examen.parrot.stores.interactors

import com.examen.parrot.stores.data.StoreRepository
import com.examen.parrot.stores.framework.Store
import com.examen.parrot.stores.framework.StoreDao
import javax.inject.Inject

class GetStores @Inject constructor(private val storeRepository: StoreRepository) {

    suspend fun getStores(token:String):List<Store>?{
        return storeRepository.getStores(token)
    }

}