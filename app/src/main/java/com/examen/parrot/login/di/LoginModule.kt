package com.examen.parrot.login.di

import com.examen.parrot.login.data.LoginDataSource
import com.examen.parrot.login.sources.LoginSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class LoginModule {

    @Binds
    abstract fun provideLoginSource(loginSource: LoginSource): LoginDataSource

}