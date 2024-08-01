package com.aquadevs.jira.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("codUser") val user: String,
    @SerializedName("pwd") val password: String
)
