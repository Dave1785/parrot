package com.examen.parrot.stores.data

import com.examen.parrot.stores.domain.RequestUpdateProduct
import com.examen.parrot.stores.domain.ResponseProducts
import com.examen.parrot.stores.domain.ResponseStore
import com.examen.parrot.stores.domain.ResponseUpdateProduct
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface StoreService{

    @GET("/api/v1/users/me")
    fun getStoresAsync(@Header("Authorization") token:String):Deferred<ResponseStore>

    @GET("/api/v1/products/")
    fun getProductsAsync(@Header("Authorization")token: String,@Query("store")storeId:String):Deferred<ResponseProducts>

    @PUT("/api/v1/products/{product_id}/availability")
    fun updateProduct(@Header("Authorization")token: String,@Path("product_id")productId:String,@Body requestUpdateProduct: RequestUpdateProduct):Deferred<ResponseUpdateProduct>
}