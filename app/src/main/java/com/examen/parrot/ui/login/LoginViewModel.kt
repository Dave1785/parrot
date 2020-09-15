package com.examen.parrot.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import android.view.View
import androidx.databinding.Bindable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.examen.parrot.data.login.LoginRepository

import com.examen.parrot.R
import com.examen.parrot.domain.login.*
import com.examen.parrot.utils.Extensions.default
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    //Data
    private var _authenticate= MutableLiveData<ResponseLogin>()
    val authenticate :LiveData<ResponseLogin>
        get() = _authenticate


    var loader=MutableLiveData<Boolean>().default(false)


    var name=MutableLiveData<String>().apply {
        value="David"
    }

    fun doLogin(loginRequestDTO: LoginRequestDTO){
        viewModelScope.launch {
          _authenticate.value= loginRepository.doLogin(loginRequestDTO)
        }
    }

    fun showLoading(isLoading:Boolean){
       loader.value=isLoading
    }



}