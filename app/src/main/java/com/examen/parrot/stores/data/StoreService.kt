package com.examen.parrot.stores.data

import com.examen.parrot.stores.domain.ResponseStore
import retrofit2.http.GET
import retrofit2.http.Header

interface StoreService{

    @GET("/api/v1/users/me")
    fun getStores(@Header("Authorization") token:String):ResponseStore
}