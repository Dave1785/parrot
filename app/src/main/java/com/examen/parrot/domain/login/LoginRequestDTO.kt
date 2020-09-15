package com.examen.parrot.domain.login

import com.google.gson.annotations.SerializedName

data class LoginRequestDTO(

    @SerializedName("username")
    var username:String,

    @SerializedName("password")
    var password:String
)