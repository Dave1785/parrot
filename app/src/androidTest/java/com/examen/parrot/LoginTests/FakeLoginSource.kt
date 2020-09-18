package com.examen.parrot.LoginTests

import com.examen.parrot.login.data.LoginDataSource
import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin
import com.examen.parrot.login.domain.ResponseValidateToken

class FakeLoginSource : LoginDataSource {

    private lateinit var mockLoginSource: MockLoginSource
    private lateinit var loginServiceMock: LoginServiceMock

    fun initService() {
        loginServiceMock = LoginServiceMock()
        loginServiceMock.setUp()
        mockLoginSource = MockLoginSource(loginServiceMock.getLoginServiceMock())
    }

    override suspend fun loginAsync(loginRequestDTO: LoginRequestDTO): ResponseLogin {
       return mockLoginSource.doLogin(loginRequestDTO)
    }

    override suspend fun validateToken(): ResponseValidateToken {
        TODO("Not yet implemented")
    }

}