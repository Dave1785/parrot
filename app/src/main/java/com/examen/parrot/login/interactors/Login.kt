package com.examen.parrot.login.interactors

import com.examen.parrot.login.data.LoginRepository
import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin
import com.examen.parrot.login.domain.ResponseValidateToken
import javax.inject.Inject

class Login @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun doLogin(requestDTO: LoginRequestDTO):ResponseLogin?{
      return  loginRepository.doLogin(requestDTO)
    }

    suspend fun validateToken(token:String):ResponseValidateToken?{
        return  loginRepository.validateToken(token)
    }

}