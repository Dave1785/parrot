package com.examen.parrot.login.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.examen.parrot.R
import com.examen.parrot.databinding.ActivityLoginBinding
import com.examen.parrot.stores.ui.MainActivity
import com.examen.parrot.login.framework.UserPreferences
import com.examen.parrot.shared.framework.RefreshWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.data=loginViewModel

        binding.userName.setText("android-challenge@parrotsoftware.io")
        binding.password.setText("8mngDhoPcB3ckV7X")


        binding.lifecycleOwner=this

    }

    override fun onResume() {
        super.onResume()


        loginViewModel.authenticate.observe(this, Observer {
            loginViewModel.showLoading(false)
            if(it!=null){
                Toast.makeText(this,"Se logeo yeihhh ${it.access}",Toast.LENGTH_LONG).show()
                UserPreferences.getInstance(this).saveUser(it)
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                showMessage(true,binding.message.root)
            }
        })
    }

    /**
     * Show message in case of error
     */
    private fun showMessage( isHide: Boolean, controls: View){

        val pos=(controls.height+10).toFloat()
        val direction = View.TRANSLATION_Y
        val position = -(pos)

        val animator = ObjectAnimator.ofFloat(controls, direction, position)
        animator.start()

        Timer("SettingUp", false).schedule(3000) {
            runOnUiThread {
                val animator = ObjectAnimator.ofFloat(controls, direction, 0f)
                animator.start()
            }
        }


    }




}

