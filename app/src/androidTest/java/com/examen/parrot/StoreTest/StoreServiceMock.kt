package com.examen.parrot.StoreTest


import com.examen.parrot.stores.data.StoreService
import com.examen.parrot.stores.domain.ResponseStore
import com.examen.parrot.stores.domain.Result
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred

class StoreServiceMock {

    private lateinit var client: StoreService
    private lateinit var responseStore: ResponseStore

    fun setUp() {
        // setup the mocked client
        client = mockk(relaxed = true)
        responseStore = ResponseStore("", Result("","", null))
        every { client.getStoresAsync(any()) } returns CompletableDeferred(responseStore)
    }

    fun getStoreServiceMock(): StoreService {
        return client
    }
}