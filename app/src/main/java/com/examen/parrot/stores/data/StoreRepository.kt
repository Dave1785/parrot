package com.examen.parrot.stores.data

import android.util.Log
import com.examen.parrot.stores.domain.ResponseStore
import com.examen.parrot.stores.source.StoreSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(private val storeSource: StoreSource){

    suspend fun getStores(token:String):ResponseStore?{
        var responseStore:ResponseStore?=null
        try {
            storeSource.getStores(token)
        }catch (e:Exception){
            Log.d("Exception","Exception $e")
        }
        return responseStore
    }

}