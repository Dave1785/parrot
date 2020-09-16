package com.examen.parrot.LoginTests

import com.examen.parrot.login.data.LoginService
import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin

class MockLoginSource(private val loginService: LoginService) {

    suspend fun doLogin(loginRequestDTO: LoginRequestDTO):ResponseLogin{
        return loginService.doLogin(loginRequestDTO).await()
    }

}