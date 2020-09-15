package com.examen.parrot.di

import com.examen.parrot.data.login.LoginDataSource
import com.examen.parrot.framework.sources.LoginSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class LoginModule {

    @Binds
    abstract fun provideLoginSource(loginSource: LoginSource):LoginDataSource
}