package com.examen.parrot.login.data

import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin
import com.examen.parrot.login.domain.ResponseValidateToken
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


/**
 * Interfaz de Parrot
 */
interface LoginService {

    @POST("/api/auth/token")
    fun doLogin(@Body requestLogin: LoginRequestDTO): Deferred<ResponseLogin>

    @GET("/api/auth/token/test")
    fun validateToken(@Header("Authorization")token: String):Deferred<ResponseValidateToken>
}

