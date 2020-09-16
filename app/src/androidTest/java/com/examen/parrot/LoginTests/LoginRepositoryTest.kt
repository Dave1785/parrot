package com.examen.parrot.LoginTests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.examen.parrot.login.data.LoginDataSource
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginRepositoryTest {

    private lateinit var loginSource: LoginDataSource

    @Before
    fun initSource(){
        loginSource= FakeLoginSource()
    }

}