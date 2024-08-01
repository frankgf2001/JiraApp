package com.aquadevs.jira.core.function

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object Retrofit {
    fun getClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()
}