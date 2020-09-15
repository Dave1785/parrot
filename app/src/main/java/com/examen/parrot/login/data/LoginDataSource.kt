package com.examen.parrot.login.data

import com.examen.parrot.login.domain.ResponseLogin
import kotlinx.coroutines.Deferred

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
interface LoginDataSource {

    suspend fun login(username: String, password: String): Deferred<ResponseLogin>
    suspend fun logout()
    fun getToken():String

}