package com.examen.parrot.data.login

import com.examen.parrot.domain.login.LoggedInUser
import com.examen.parrot.domain.login.ResponseLogin
import com.examen.parrot.domain.login.Result
import kotlinx.coroutines.Deferred

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
interface LoginDataSource {

    suspend fun login(username: String, password: String): Deferred<ResponseLogin>
    suspend fun logout()

}