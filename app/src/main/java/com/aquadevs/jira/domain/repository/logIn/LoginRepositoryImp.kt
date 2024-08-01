package com.aquadevs.jira.domain.repository.logIn

import com.aquadevs.jira.data.model.login.LoginRequest
import com.aquadevs.jira.domain.services.login.LoginService
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val loginService: LoginService,
) : LoginRepository{
    override suspend fun login(loginRequest: LoginRequest): Boolean {
        return try {
            val rpt = loginService.login(loginRequest)
            if (rpt.isSuccessful) rpt.body()?.status == 200
            else false
        }catch (e:Exception){
            false
        }
    }

}