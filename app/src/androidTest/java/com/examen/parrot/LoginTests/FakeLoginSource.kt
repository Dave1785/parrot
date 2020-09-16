package com.examen.parrot.LoginTests

import com.examen.parrot.login.data.LoginDataSource
import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin

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

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getToken(): String {
        TODO("Not yet implemented")
    }

}