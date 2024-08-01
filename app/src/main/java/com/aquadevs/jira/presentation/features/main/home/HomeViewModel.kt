package com.aquadevs.jira.presentation.features.main.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aquadevs.jira.domain.usecase.home.GetHomeUseCase
import com.aquadevs.jira.domain.usecase.profile.GetPersonUseCase
import com.aquadevs.jira.presentation.model.BoardDto
import com.aquadevs.jira.presentation.model.PersonDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,
    private val getPersonUseCase: GetPersonUseCase
) : ViewModel() {
    private val _personDto = MutableLiveData<PersonDto>()
    val personDto: LiveData<PersonDto> = _personDto

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    private val _listBoard = mutableStateListOf<BoardDto>()
    val listBoard: List<BoardDto> = _listBoard

    private val _showAdvancedSearchDialog = MutableLiveData<Boolean>()
    val showAdvancedSearchDialog: LiveData<Boolean> = _showAdvancedSearchDialog

    private var _messageToast = MutableLiveData<Int>()
    val messageToast: LiveData<Int> = _messageToast

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    private val _projectCode = MutableLiveData<String>()
    val projectCode: LiveData<String> = _projectCode

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _state = MutableLiveData<String>()
    val state: LiveData<String> = _state

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private val _projectIcon = MutableLiveData<String>()
    val projectIcon: LiveData<String> = _projectIcon

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endingDate = MutableLiveData<String>()
    val endingDate: LiveData<String> = _endingDate

    init {
        getInfoUser()
    }

    fun searchBoard(text: String) {
        _search.value = text
    }

    private fun getInfoUser() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val resPerson = getPersonUseCase()
            val listBoard = getHomeUseCase.getListBoard()
            withContext(Dispatchers.Main) {
                _personDto.value = resPerson
                listBoard.forEach { item ->
                    _listBoard.add(item)
                }
                _isLoading.value = false
            }
        }
    }

    fun detectAction(idAction: Int, bool: Boolean = true) {
        if (idAction == 1) _showAdvancedSearchDialog.value = bool
    }

    fun cleanDialog(){
        _projectCode.value = ""
        _name.value = ""
        _state.value = ""
        _category.value = ""
        _projectIcon.value = ""
        _startDate.value = ""
        _endingDate.value = ""
    }

    fun validateHome(
        projectCode: String? = null,
        name: String? = null,
        state: String? = null,
        category: String? = null,
        projectIcon: String? = null,
        startDate: String? = null,
        endingDate: String? = null,
    ) {
        if (projectCode != null) _projectCode.value = projectCode ?: ""
        if (name != null) _name.value = name ?: ""
        if (state != null) _state.value = state ?: ""
        if (category != null) _category.value = category ?: ""
        if (projectIcon != null) _projectIcon.value = projectIcon ?: ""
        if (startDate != null) _startDate.value = startDate ?: ""
        if (endingDate != null) _endingDate.value = endingDate ?: ""
    }
}