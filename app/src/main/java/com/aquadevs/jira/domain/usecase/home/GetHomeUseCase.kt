package com.aquadevs.jira.domain.usecase.home

import androidx.compose.ui.graphics.Color
import com.aquadevs.jira.R
import com.aquadevs.jira.presentation.model.BoardDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(

) {
    suspend fun getListBoard(): List<BoardDto> {
        delay(1200)
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