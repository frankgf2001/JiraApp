package com.aquadevs.jira.domain.services.login

import com.aquadevs.jira.core.function.Constants.API_LOGIN
import com.aquadevs.jira.data.model.login.LoginRequest
import com.aquadevs.jira.data.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("$API_LOGIN/getLogin")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

}