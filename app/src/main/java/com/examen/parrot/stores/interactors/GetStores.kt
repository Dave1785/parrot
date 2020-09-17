package com.examen.parrot.stores.interactors


import com.examen.parrot.stores.data.StoreRepository
import com.examen.parrot.stores.data.onUpdateDataListener
import com.examen.parrot.stores.framework.StoreEntity
import javax.inject.Inject

class GetStores @Inject constructor(private val storeRepository: StoreRepository) {

    private var onUpdateDataListener: onUpdateDataListener?=null
     suspend fun getStores(token: String):List<StoreEntity>? {
       return storeRepository.getStores(token)
    }

    suspend fun getStoresRefresh(token: String){
        val stores=storeRepository.getStores(token)
        onUpdateDataListener?.onDataUpdate(stores)
    }

    fun setListenerData(onUpdateDataListener: onUpdateDataListener){
        this.onUpdateDataListener=onUpdateDataListener
    }

}