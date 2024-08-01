package com.aquadevs.jira.domain.usecase.login

import com.aquadevs.jira.domain.functions.LogInScopeFunctions.toLoginRequest
import com.aquadevs.jira.domain.repository.logIn.LoginRepository
import com.aquadevs.jira.presentation.model.PersonDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetLogInUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(personDto: PersonDto):Boolean{
        delay(2000)
        loginRepository.login(personDto.toLoginRequest())
        return personDto.codUser == "jira@gmail.com" && personDto.password == "02082024"
    }
}