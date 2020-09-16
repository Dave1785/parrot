package com.examen.parrot.LoginTests

import com.examen.parrot.login.data.LoginService
import com.examen.parrot.login.domain.ResponseLogin
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred

class LoginServiceMock {

    private lateinit var client: LoginService
    private lateinit var responseLogin: ResponseLogin

    fun setUp() {
        // setup the mocked client
        client = mockk(relaxed = true)
        responseLogin = ResponseLogin("TestToken","RefreshTokenDenegado")
        every { client.doLogin(any()) } returns CompletableDeferred(responseLogin)
    }

    fun getLoginServiceMock():LoginService{
        return client
    }


}