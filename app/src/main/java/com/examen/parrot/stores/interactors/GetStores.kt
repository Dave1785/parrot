package com.examen.parrot.stores.interactors

import com.examen.parrot.stores.data.StoreRepository
import com.examen.parrot.stores.data.OnUpdateDataListener
import com.examen.parrot.stores.framework.Product
import com.examen.parrot.stores.framework.StoreEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetStores @Inject constructor(private val storeRepository: StoreRepository) {

    private var onUpdateDataListener: OnUpdateDataListener? = null

    suspend fun getStores(token: String): HashMap<String, StoreEntity> {
        return getData(storeRepository.getStores(token))
    }

    suspend fun getProducts(token: String, storeId: String): List<Product>? {
        return storeRepository.getProducts(token, storeId)
    }

    fun setListener(onUpdateDataListener: OnUpdateDataListener) {
        this.onUpdateDataListener = onUpdateDataListener
    }

    fun updateData() {
        onUpdateDataListener?.onDataUpdate()
    }

    /**
     * Parse data
     */
    private fun getData(storeKeys: List<StoreEntity>?): HashMap<String, StoreEntity> {

        val expandableListDetail = HashMap<String, StoreEntity>()

        if (storeKeys != null) {
            for (store in storeKeys) {
                expandableListDetail[store.name] = store
            }
        }

        return expandableListDetail
    }


}