package com.examen.parrot.stores.data

import com.examen.parrot.stores.domain.RequestUpdateProduct
import com.examen.parrot.stores.domain.ResponseUpdateProduct
import com.examen.parrot.stores.framework.ProductDao
import com.examen.parrot.stores.framework.StoreDao
import com.examen.parrot.stores.source.StoreRemoteSource
import com.examen.parrot.utils.performGetOperation
import com.examen.parrot.utils.performGetOperationProduct
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(
    private val storeSource: StoreRemoteSource,
    private val storeDao: StoreDao,private val productDao: ProductDao
) {

    suspend fun getStores(token: String) = performGetOperation(
        databaseQuery = { storeDao.getAllStores() },
        networkCall = {storeSource.getStoresAsync(token)},
        saveCallResult = { storeDao.insertAll(it) }
    )


    suspend fun getProducts(token: String,storeId:String)= performGetOperationProduct(
        databaseQuery = {productDao.getAllProducts()},
        networkCall = {storeSource.getProducts(token,storeId)},
        saveCallResult = {productDao.insertAll(it)}

    )

    suspend fun updateProduct(token: String,productId:String,requestUpdateProduct: RequestUpdateProduct):ResponseUpdateProduct?{
       return storeSource.updateProductStatus(token,productId,requestUpdateProduct).data
    }


}