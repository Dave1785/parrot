package com.examen.parrot.login.data

import android.util.Log
import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin
import com.examen.parrot.login.sources.LoginSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

@Singleton
class LoginRepository @Inject constructor(private val loginSource: LoginSource) {

    suspend fun doLogin(requestDTO: LoginRequestDTO): ResponseLogin?{
        var responseLogin: ResponseLogin?=null
        try{
           responseLogin= loginSource.loginService.doLogin(requestDTO).await()
        }catch (e:Exception){
            Log.d("Fail","Fail")
        }
        return responseLogin
    }


}