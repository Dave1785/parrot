package com.examen.parrot.login.sources

import com.examen.parrot.login.data.LoginDataSource
import com.examen.parrot.login.data.LoginService
import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin
import kotlinx.coroutines.Deferred
import javax.inject.Inject


class LoginSource @Inject constructor(val loginService: LoginService) : LoginDataSource {

    override suspend fun loginAsync(username: String, password: String): Deferred<ResponseLogin> {
       return loginService.doLogin(LoginRequestDTO(username,password))
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getToken(): String {
        TODO("Not yet implemented")
    }


}