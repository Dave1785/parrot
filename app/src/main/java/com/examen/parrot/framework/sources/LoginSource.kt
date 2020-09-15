package com.examen.parrot.framework.sources

import com.examen.parrot.data.login.LoginDataSource
import com.examen.parrot.data.login.LoginService
import com.examen.parrot.domain.login.LoggedInUser
import com.examen.parrot.domain.login.LoginRequestDTO
import com.examen.parrot.domain.login.ResponseLogin
import kotlinx.coroutines.Deferred
import javax.inject.Inject


class LoginSource @Inject constructor(val loginService:LoginService) :LoginDataSource{

    override suspend fun login(username: String, password: String): Deferred<ResponseLogin> {
       return loginService.doLogin(LoginRequestDTO(username,password))
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }


}