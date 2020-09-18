package com.examen.parrot.login.data

import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin
import com.examen.parrot.login.domain.ResponseValidateToken

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
interface LoginDataSource {

    suspend fun loginAsync(loginRequestDTO: LoginRequestDTO): ResponseLogin

    suspend fun validateToken(token:String):ResponseValidateToken

}