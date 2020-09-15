package com.examen.parrot.splash.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.examen.parrot.R
import com.examen.parrot.login.framework.UserPreferences
import com.examen.parrot.login.ui.LoginActivity
import com.examen.parrot.stores.ui.MainActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity :AppCompatActivity(){

    private lateinit var token:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        token = UserPreferences.getInstance(this).getValue(UserPreferences.DataType.TOKEN) as String
    }

    override fun onResume() {
        super.onResume()
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
    }

}