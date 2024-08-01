package com.aquadevs.jira.domain.repository.logIn

import com.aquadevs.jira.data.model.login.LoginRequest

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): Boolean
}