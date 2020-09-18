package com.examen.parrot.login.sources

import com.examen.parrot.login.data.LoginDataSource
import com.examen.parrot.login.data.LoginService
import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin
import com.examen.parrot.login.domain.ResponseValidateToken
import javax.inject.Inject


class LoginSource @Inject constructor(val loginService: LoginService) : LoginDataSource {

    override suspend fun loginAsync(loginRequestDTO: LoginRequestDTO):ResponseLogin {
       return loginService.doLogin(loginRequestDTO).await()
    }

    override suspend fun validateToken(token:String): ResponseValidateToken {
        return loginService.validateToken("Bearer $token").await()
    }


}