package com.examen.parrot.login.domain

import com.google.gson.annotations.SerializedName

data class LoginRequestDTO(

    @SerializedName("username")
    var username:String?,

    @SerializedName("password")
    var password:String?
)