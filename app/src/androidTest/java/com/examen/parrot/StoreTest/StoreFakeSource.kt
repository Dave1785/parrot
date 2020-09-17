package com.examen.parrot.StoreTest

import com.examen.parrot.stores.data.StoreService
import com.examen.parrot.utils.BaseDataSource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class StoreFakeSource : BaseDataSource() {

    private lateinit var storeServiceMock: StoreServiceMock
    private lateinit var storeService: StoreService

    @Before
    fun initSource() {
        storeServiceMock = StoreServiceMock()
        storeServiceMock.setUp()
        storeService = storeServiceMock.getStoreServiceMock()
    }

    @Test
    fun testGetStores() {
        val response = storeService.getStoresAsync("")
        Assert.assertFalse(response.isCompleted)
    }
}