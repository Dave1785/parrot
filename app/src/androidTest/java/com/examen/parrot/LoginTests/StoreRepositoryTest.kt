package com.examen.parrot.LoginTests

import com.examen.parrot.login.domain.LoginRequestDTO
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert

class StoreRepositoryTest {

    private lateinit var fakeLoginSource: FakeLoginSource

    @Before
    fun initSource() {
        fakeLoginSource = FakeLoginSource()
        fakeLoginSource.initService()
    }

    @Test
    fun testLogin() = runBlockingTest {
        val response = fakeLoginSource.loginAsync(LoginRequestDTO("Test", "sdsd"))
        Assert.assertFalse(response.access == "RefreshTokenValido")
    }


}