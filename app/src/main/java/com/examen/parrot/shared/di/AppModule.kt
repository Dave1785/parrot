package com.examen.parrot.shared.di

import android.content.Context
import com.examen.parrot.login.data.LoginService
import com.examen.parrot.stores.data.StoreRepository
import com.examen.parrot.stores.data.StoreService
import com.examen.parrot.stores.framework.ProductDao
import com.examen.parrot.stores.framework.StoreDao
import com.examen.parrot.stores.framework.StoreDataBase
import com.examen.parrot.stores.source.StoreRemoteSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getMoshiInstance():Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideGson(): Gson {
      return  GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("http://api-staging.parrot.rest")
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService =retrofit.create(LoginService::class.java)

    @Provides
    fun provideStoreService(retrofit: Retrofit): StoreService =retrofit.create(StoreService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = StoreDataBase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: StoreDataBase) = db.storeDao()

    @Singleton
    @Provides
    fun provideProductDao(db: StoreDataBase) = db.productDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: StoreRemoteSource,
                          localDataSource: StoreDao,productDao: ProductDao) =
        StoreRepository(remoteDataSource, localDataSource,productDao)



}