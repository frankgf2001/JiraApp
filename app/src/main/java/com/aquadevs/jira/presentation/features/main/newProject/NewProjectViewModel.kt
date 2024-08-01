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
}