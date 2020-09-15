package com.examen.parrot.ui.login

import android.app.Activity
import android.content.Intent
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
import com.examen.parrot.ui.login.categories.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    private val loginViewModel:LoginViewModel by viewModels()
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.data=loginViewModel

        binding.userName.setText("android-challenge@parrotsoftware.io")
        binding.password.setText("8mngDhoPcB3ckV7X")

    }

    override fun onResume() {
        super.onResume()

        binding.login.setOnClickListener {
            loginViewModel.showLoading(true)
            binding.invalidateAll()
            loginViewModel.doLogin(LoginRequestDTO(binding.userName.text.toString(),binding.password.text.toString()))
        }

        loginViewModel.authenticate.observe(this, Observer {
            loginViewModel.showLoading(false)
            binding.invalidateAll()
            if(it!=null){
                Toast.makeText(this,"Se logeo yeihhh ${it.access}",Toast.LENGTH_LONG).show()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        })
    }


}

