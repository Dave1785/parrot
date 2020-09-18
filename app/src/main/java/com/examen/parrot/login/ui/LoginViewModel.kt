package com.examen.parrot.login.ui

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.parrot.login.domain.LoginRequestDTO
import com.examen.parrot.login.domain.ResponseLogin
import com.examen.parrot.login.interactors.Login
import com.examen.parrot.utils.Extensions.default
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(private val login:Login) : ViewModel() {

    //Data
    private var _authenticate= MutableLiveData<ResponseLogin>()
    val authenticate :LiveData<ResponseLogin>
        get() = _authenticate

    var loader=MutableLiveData<Boolean>().default(false)


    var name=MutableLiveData<String>().default("")

    var password=MutableLiveData<String>().default("")

    fun doLogin(view: View){
        showLoading(true)
        viewModelScope.launch {
          _authenticate.value= login.doLogin(LoginRequestDTO(name.value,password.value))
        }
    }

    fun showLoading(isLoading:Boolean){
       loader.value=isLoading
    }


}