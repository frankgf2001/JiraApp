package com.aquadevs.jira.presentation.model

data class PersonDto(
    val codUser:String = "",
    val password:String = "",
    val userName:String = "",
    val userSurname:String = "",
    val companyName:String = "",
    val positionCompany:String = "",
    val cellPhone:String = "",
    val email:String = "",
    val urlProfile:String = ""
)
