package com.aquadevs.jira.presentation.features.main.newProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aquadevs.jira.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NewProjectViewModel @Inject constructor(

) : ViewModel() {

    private val _projectName = MutableLiveData<String>()
    val projectName: LiveData<String> = _projectName

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _projectState = MutableLiveData<String>()
    val projectState: LiveData<String> = _projectState

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _isSearch = MutableLiveData<Boolean>()
    val isSearch: LiveData<Boolean> = _isSearch


    private val listIcon = mutableListOf(
        R.drawable.icon_calendar,
        R.drawable.icon_briefcase,
        R.drawable.icon_advertising,
        R.drawable.icon_insurance,
        R.drawable.icon_folder,
        R.drawable.icon_email,
        R.drawable.icon_cloud_computing
    )

    private  val _randomIcon = MutableLiveData<Int>()
    val randomIcon : LiveData<Int> = _randomIcon

    fun generateRandomIcon(){
        val index = Random.nextInt(listIcon.size)
        _randomIcon.value = listIcon[index]
    }

    fun validateNewProject(
        projectName: String? = null,
        description: String? = null,
        projectState: String? = null,
        startDate: String? = null,
        endDate: String? = null,
        isSearch: Boolean? = null
    ) {
        if (projectName != null) _projectName.value = projectName ?: ""
        if (description != null) _description.value = description ?: ""
        if (projectState != null) _projectState.value = projectState ?: ""
        if (startDate != null) _startDate.value = startDate ?: ""
        if (endDate != null) _endDate.value = endDate ?: ""
        if (isSearch != null) _isSearch.value = isSearch ?: false
    }
}