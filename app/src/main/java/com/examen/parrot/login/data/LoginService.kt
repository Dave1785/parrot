package com.examen.parrot.login.data

import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.LoginResult
import com.examen.parrot.login.domain.ResponseLogin
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
    fun doLogin(@Body requestLogin: LoginRequestDTO): Deferred<ResponseLogin>

}

