package com.aquadevs.jira.presentation.features.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aquadevs.jira.domain.usecase.profile.GetPersonUseCase
import com.aquadevs.jira.presentation.model.PersonDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase
) : ViewModel() {
    private val _personDto = MutableLiveData<PersonDto>()
    val personDto : LiveData<PersonDto> = _personDto

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getInfo()
    }

    fun validateProfile(
        names:String? = null,
        surnames:String? = null,
        companyName:String? = null,
        positionCompany:String? = null,
        cellPhone:String? = null,
        email:String? = null,
    ) {
        if (names != null){
            _personDto.value = personDto.value?.copy(userName = names)
        }
        if (surnames != null){
            _personDto.value = personDto.value?.copy(userSurname = surnames)
        }
        if (companyName != null){
            _personDto.value = personDto.value?.copy(companyName = companyName)
        }
        if (positionCompany != null){
            _personDto.value = personDto.value?.copy(positionCompany = positionCompany)
        }
        if (cellPhone != null){
            _personDto.value = personDto.value?.copy(cellPhone = cellPhone)
        }
        if (email != null){
            _personDto.value = personDto.value?.copy(email = email)
        }
    }

    private fun getInfo(){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val resPerson = getPersonUseCase()
            withContext(Dispatchers.Main){
                _personDto.value = resPerson
                _isLoading.value = false
            }
        }
    }
}