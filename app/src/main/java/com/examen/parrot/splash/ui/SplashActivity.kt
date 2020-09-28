package com.examen.parrot.splash.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.examen.parrot.R
import com.examen.parrot.login.framework.UserPreferences
import com.examen.parrot.login.ui.LoginActivity
import com.examen.parrot.login.ui.LoginViewModel
import com.examen.parrot.stores.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule


@AndroidEntryPoint
class SplashActivity :AppCompatActivity(){


    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var token:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        token = UserPreferences.getInstance(this).getValue(UserPreferences.DataType.TOKEN) as String

        loginViewModel.validateToken(token)
    }

    override fun onResume() {
        super.onResume()

        loginViewModel.validateToken.observe(this, androidx.lifecycle.Observer {

            if(it!=null && it.status=="ok"){
                if(token.isNullOrEmpty()){
                    Timer("SettingUp", false).schedule(5000) {
                        startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                        finish()
                    }
                }else{
                    Timer("SettingUp", false).schedule(5000) {
                        startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                        finish()
                    }

                }
            }else{
                if(token.isNullOrEmpty()){

                    Toast.makeText(this,"Tu token se caduco inicia sesión nuevamente",Toast.LENGTH_LONG).show()
                    Timer("SettingUp", false).schedule(5000) {
                        startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                        finish()
                    }
                }else{
                    Timer("SettingUp", false).schedule(5000) {
                        startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                        finish()
                    }

                }
            }

        })

    }

}