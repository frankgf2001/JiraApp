package com.aquadevs.jira.presentation.features.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aquadevs.jira.R
import com.aquadevs.jira.core.function.DataStorePreferences.getValuesRememberPassword
import com.aquadevs.jira.core.function.DataStorePreferences.saveValuesRememberPassword
import com.aquadevs.jira.core.function.General.isValidEmail
import com.aquadevs.jira.domain.usecase.login.GetLogInUseCase
import com.aquadevs.jira.presentation.model.PersonDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLogInUseCase: GetLogInUseCase
) : ViewModel() {
    private var _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private var _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _enabledButton = MutableLiveData<Boolean>()
    val enabledButton: LiveData<Boolean> = _enabledButton

    private var _isRememberPassword = MutableLiveData<Boolean>()
    val isRememberPassword: LiveData<Boolean> = _isRememberPassword

    private var _messageToast = MutableLiveData<Int>()
    val messageToast: LiveData<Int> = _messageToast

    fun validateLogin(
        context: Context,
        user: String? = null,
        password: String? = null,
        bool: Boolean? = null
    ) {
        if (user != null) _user.value = user ?: ""
        if (password != null) _password.value = password ?: ""
        if (bool != null) _isRememberPassword.value = bool ?: false
        _enabledButton.value = isEnabledLogin(this.user.value, this.password.value)

        viewModelScope.launch(Dispatchers.IO) {
            context.saveValuesRememberPassword(
                user = this@LoginViewModel.user.value ?: "",
                password = this@LoginViewModel.password.value ?: "",
                isRememberPassword = this@LoginViewModel._isRememberPassword.value ?: false
            )
        }
    }

    fun signIn(onResponse: () -> Unit) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isLogin = getLogInUseCase(
                    PersonDto(
                        codUser = _user.value ?: "",
                        password = _password.value ?: ""
                    )
                )

                withContext(Dispatchers.Main) {
                    if (!isLogin) _messageToast.value = R.string.incorrectCredentials
                    else onResponse()
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _messageToast.value = R.string.anErrorOcurred
                    _isLoading.value = false
                }
            }
        }
    }

    fun getDataStore(context: Context) {
        viewModelScope.launch {
            context.getValuesRememberPassword { user, pass, isRemember ->
                _user.value = user.ifEmpty { this@LoginViewModel.user.value ?: "" }
                _password.value = pass.ifEmpty { this@LoginViewModel.password.value ?: "" }
                _enabledButton.value = isEnabledLogin(
                    user = user.ifEmpty { this@LoginViewModel.user.value ?: "" },
                    password = pass.ifEmpty { this@LoginViewModel.password.value ?: "" }
                )
                _isRememberPassword.value = isRemember
            }
        }
    }

    private fun isEnabledLogin(user: String?, password: String?): Boolean {
        val pUser = user ?: ""
        val pPassword = password ?: ""
        return pUser.isValidEmail() && pPassword.length > 4
    }

    fun detectAction(idAction: Int) {
        if (idAction == 0) _messageToast.value = 0
    }
}