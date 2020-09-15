package com.examen.parrot.stores.interactors

import com.examen.parrot.stores.data.StoreRepository
import com.examen.parrot.stores.domain.ResponseStore
import javax.inject.Inject

class GetStores @Inject constructor(private val storeRepository: StoreRepository) {

    suspend fun getStores(token:String):ResponseStore?{
        return storeRepository.getStores(token)
    }

}