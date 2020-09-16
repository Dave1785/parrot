package com.examen.parrot.shared.di

import android.content.Context
import com.examen.parrot.login.data.LoginService
import com.examen.parrot.stores.data.StoreService
import com.examen.parrot.stores.framework.StoreDataBase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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


    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("http://api-staging.parrot.rest")
        .addConverterFactory(MoshiConverterFactory.create(getMoshiInstance()))
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

}