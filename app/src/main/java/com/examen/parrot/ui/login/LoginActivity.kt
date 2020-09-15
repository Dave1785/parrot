package com.examen.parrot.ui.login

import android.app.Activity
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.examen.parrot.R
import com.examen.parrot.databinding.ActivityLoginBinding
import com.examen.parrot.domain.login.LoggedInUserView
import com.examen.parrot.domain.login.LoginRequestDTO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    private val loginViewModel:LoginViewModel by viewModels()
    private lateinit var bindingLogin:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin=DataBindingUtil.setContentView(this,R.layout.activity_login)

        bindingLogin.data=loginViewModel
    }

    override fun onResume() {
        super.onResume()

        bindingLogin.login.setOnClickListener {
            loginViewModel.showLoading(true)
            bindingLogin.invalidateAll()
            loginViewModel.doLogin(LoginRequestDTO(bindingLogin.name.toString(),bindingLogin.password.toString()))
        }

        loginViewModel.authenticate.observe(this, Observer {
            loginViewModel.showLoading(false)
            bindingLogin.invalidateAll()
            bindingLogin.loadingView.root.visibility=View.GONE
            if(it!=null){
                Toast.makeText(this,"Se logeo yeihhh ${it.access}",Toast.LENGTH_LONG).show()
            }
        })
    }


}

