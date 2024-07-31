package com.aquadevs.jira.presentation.features.main.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aquadevs.jira.R
import com.aquadevs.jira.presentation.model.BoardDto
import com.aquadevs.jira.presentation.model.PersonDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _personDto = MutableLiveData<PersonDto>()
    val personDto: LiveData<PersonDto> = _personDto

    private val _listBoard = mutableStateListOf<BoardDto>()
    val listBoard: List<BoardDto> = _listBoard

    init {
        getInfoUser()
        getListBoard()
    }

    private fun getInfoUser() {
        viewModelScope.launch(Dispatchers.IO) {
            //delay(500)
            withContext(Dispatchers.Main) {
                _personDto.value = PersonDto(
                    codUser = "frankgutierrezdev@gmail,com",
                    userName = "Frank Gutierrez",
                    companyName = "CEO LVL Consulting",
                    urlProfile = "https://s3-alpha-sig.figma.com/img/af22/e37f/c59a84890081e9133704fdb55b8401e3?Expires=1723420800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=lKGdBelAq2GU04i92kHOk1KyeohtpJi1ldk1oOzFvgorevwMdAc5f8H1nT5ssOpg9~~O4t205HKumg10Pb-Ed4PNdZdaH7r-HjBt3P7e7QSra3yl21XsbrInoWHLZ5AAjv2UtUioOavYIW3AMJdhlSZZD39Qcg8bG2s4McOh-Z0462jcvlvoB0~4Exm3yj6rZydJgOW1N0hh0rC1v-BrtQurdbvejFw2i-AsrvsfPsjKkZige2Z~g7c~Ixf6IpzTIJ4vDkrenFeeN58JWe3s4X4fsCt-TujINy-k5p-W-6Ze5ZSD7of0gnnRX96VoYQ24ho38upzfAEVvMtDcSKWMg__"
                )
            }
        }
    }

    private fun getListBoard() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                listBoard().forEach { item ->
                    _listBoard.add(item)
                }
            }
        }
    }

    private fun listBoard(): List<BoardDto> {
        return mutableListOf(
            BoardDto(
                idBoard = 1,
                icon = R.drawable.icon_folder,
                nameItem = "Poryecto de App",
                codItem = "ATA-1",
                stateDescription = "PLANIFICACIÓN",
                stateCode = "PL",
                colorState = getColorState("PL")
            ),
            BoardDto(
                idBoard = 2,
                icon = R.drawable.icon_insurance,
                nameItem = "Diseño de RR.SS.",
                codItem = "PA-21",
                stateDescription = "EN CURSO",
                stateCode = "EC",
                colorState = getColorState("EC")
            ),
            BoardDto(
                idBoard = 3,
                icon = R.drawable.icon_briefcase,
                nameItem = "Programación de...",
                codItem = "PA-2",
                stateDescription = "EN REVISIÓN",
                stateCode = "ER",
                colorState = getColorState("ER")
            ),
            BoardDto(
                idBoard = 4,
                icon = R.drawable.icon_advertising,
                nameItem = "Control de calidad...",
                codItem = "ATA-1",
                stateDescription = "FINALIZADO",
                stateCode = "FN",
                colorState = getColorState("FN")
            ),
            BoardDto(
                idBoard = 5,
                icon = R.drawable.icon_email,
                nameItem = "Notificaciones de...",
                codItem = "ATA-1",
                stateDescription = "PLANIFICACIÓN",
                stateCode = "PL",
                colorState = getColorState("PL")
            ),
            BoardDto(
                idBoard = 6,
                icon = R.drawable.icon_calendar,
                nameItem = "Pago de ventanilla",
                codItem = "PA-2",
                stateDescription = "EN REVISIÓN",
                stateCode = "ER",
                colorState = getColorState("ER")
            ),
        )
    }

    private fun getColorState(codState: String): Color {
        return when (codState) {
            "PL" -> Color(0xFFE9E9E9)
            "EC" -> Color(0xFFFEFCCE)
            "ER" -> Color(0xFFD7FFC4)
            "FN" -> Color(0xFFCEE3FE)
            else -> Color.Transparent
        }
    }
}