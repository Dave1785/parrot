package com.examen.parrot.stores.data

import com.examen.parrot.stores.domain.ResponseStore
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header

interface StoreService{

    @GET("/api/v1/users/me")
    fun getStoresAsync(@Header("Authorization") token:String):Deferred<ResponseStore>
}