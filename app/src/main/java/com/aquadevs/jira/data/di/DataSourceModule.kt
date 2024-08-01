package com.aquadevs.jira.data.di

import com.aquadevs.jira.core.function.Constants.RETRO_URL
import com.aquadevs.jira.core.function.Retrofit.getClient
import com.aquadevs.jira.domain.services.login.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(RETRO_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    @Singleton
    @Provides
    fun apiServiceLogin(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }
}