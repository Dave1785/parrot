package com.examen.parrot.splash.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.examen.parrot.login.interactors.Login

class SplashViewModel @ViewModelInject constructor(private val login: Login) : ViewModel() {

}