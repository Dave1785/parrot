package com.examen.parrot.stores.interactors

import com.examen.parrot.stores.data.StoreRepository
import com.examen.parrot.stores.domain.Store
import com.examen.parrot.stores.framework.StoreEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetStores @Inject constructor(private val storeRepository: StoreRepository) {

    suspend fun getStores(token:String):List<StoreEntity>?{
        var response:List<StoreEntity>?=null
        withContext(Dispatchers.IO){
          response= storeRepository.getStores(token)
        }
     return   response
    }

}