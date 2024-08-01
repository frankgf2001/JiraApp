package com.aquadevs.jira.domain.functions

import com.aquadevs.jira.data.model.login.LoginRequest
import com.aquadevs.jira.presentation.model.PersonDto

object LogInScopeFunctions {
    fun PersonDto.toLoginRequest(): LoginRequest {
        return LoginRequest(
            user = this.codUser,
            password = this.password
        )
    }
}