package com.examen.parrot.stores.interactors

import androidx.lifecycle.LiveData
import com.examen.parrot.stores.data.StoreRepository
import com.examen.parrot.stores.domain.Store
import com.examen.parrot.stores.framework.StoreEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetStores @Inject constructor(private val storeRepository: StoreRepository) {

    suspend fun getStores(token: String):Any? {
        var response:Any?= null

        return response
    }

}