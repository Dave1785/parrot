package com.examen.parrot.data.login

import com.examen.parrot.domain.login.LoggedInUser
import com.examen.parrot.domain.login.LoginRequestDTO
import com.examen.parrot.domain.login.LoginResult
import com.examen.parrot.domain.login.ResponseLogin
import kotlinx.coroutines.Deferred
import retrofit2.http.*

/*private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()*/

/**
 * Interfaz de Parrot
 */
interface LoginService {

    @POST("/api/auth/token")
    fun doLogin(@Body requestLogin:LoginRequestDTO): Deferred<ResponseLogin>

}

