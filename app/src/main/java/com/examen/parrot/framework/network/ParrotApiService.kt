package com.examen.parrot.framework.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://192.168.1.101:54413/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Retrofit Instance
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * Interfaz de Parrot
 */
interface ParrotApiService {

    @POST("/api/auth/token")
    fun doLogin()

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
@Module
@InstallIn(ActivityComponent::class)
object ParrotApi {

    @Provides
    fun getParrotService():ParrotApiService{
        val parrotService : ParrotApiService by lazy { retrofit.create(ParrotApiService::class.java) }
        return parrotService
    }

}