package com.examen.parrot.stores.di

import com.examen.parrot.stores.data.StoreDataSource
import com.examen.parrot.stores.source.StoreRemoteSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@InstallIn(ActivityComponent::class)
@Module
abstract class StoreModule {

    @Binds
    abstract fun provideStoreSource(storeSource: StoreRemoteSource): StoreDataSource


}