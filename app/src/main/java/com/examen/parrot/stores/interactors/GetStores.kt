package com.examen.parrot.stores.interactors


import com.examen.parrot.stores.data.StoreRepository
import com.examen.parrot.stores.data.onUpdateDataListener
import com.examen.parrot.stores.framework.StoreEntity
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetStores @Inject constructor(private val storeRepository: StoreRepository) {

    private var onUpdateDataListener: onUpdateDataListener?=null

     suspend fun getStores(token: String):HashMap<String, List<String>> {
       return getData(storeRepository.getStores("Bearer $token"))
    }

    suspend fun getStoresRefresh(token: String){
        val stores=storeRepository.getStores("Bearer $token")
        onUpdateDataListener?.onDataUpdate(getData(stores))
    }

    fun setListener(onUpdateDataListener: onUpdateDataListener){
        this.onUpdateDataListener=onUpdateDataListener
    }

    private fun getData(storeKeys:List<StoreEntity>?): HashMap<String, List<String>> {

        val expandableListDetail = HashMap<String, List<String>>()

        if (storeKeys != null) {
            for (store in storeKeys){
                val storeList: MutableList<String> = ArrayList()
                storeList.add("Store")
                expandableListDetail[store.name] = storeList
            }
        }

        return expandableListDetail
    }


}