package com.examen.parrot.stores.data

import com.examen.parrot.stores.domain.ResponseStore
import kotlinx.coroutines.Deferred

interface StoreDataSource {

    suspend fun getStoresAsync(token:String):ResponseStore?

}