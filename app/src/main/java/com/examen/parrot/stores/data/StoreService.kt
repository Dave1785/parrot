package com.examen.parrot.stores.data

import com.examen.parrot.stores.domain.ResponseProducts
import com.examen.parrot.stores.domain.ResponseStore
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StoreService{

    @GET("/api/v1/users/me")
    fun getStoresAsync(@Header("Authorization") token:String):Deferred<ResponseStore>

    @GET("/api/v1/products/")
    fun getProductsAsync(@Header("Authorization")token: String,@Query("store")storeId:String):Deferred<ResponseProducts>
}