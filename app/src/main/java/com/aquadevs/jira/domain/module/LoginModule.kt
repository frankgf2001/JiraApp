package com.aquadevs.jira.domain.module

import com.aquadevs.jira.domain.repository.logIn.LoginRepository
import com.aquadevs.jira.domain.repository.logIn.LoginRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {
    @Singleton
    @Binds
    abstract fun login(loginRepositoryImp: LoginRepositoryImp): LoginRepository
}