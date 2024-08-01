package com.aquadevs.jira.domain.usecase.profile

import com.aquadevs.jira.presentation.model.PersonDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetPersonUseCase @Inject constructor (

)  {
    suspend operator fun invoke():PersonDto{
        delay(1200)
        return PersonDto(
            codUser = "frankgutierrezdev@gmail,com",
            userName = "Frank Anderson",
            userSurname = "Gutierrez Felipa",
            companyName = "CEO LVL Consulting",
            positionCompany = "Android developer",
            cellPhone = "+51 963880058",
            email = "frankgutierrezdev@gmail.com",
            urlProfile = "https://s3-alpha-sig.figma.com/img/af22/e37f/c59a84890081e9133704fdb55b8401e3?Expires=1723420800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=lKGdBelAq2GU04i92kHOk1KyeohtpJi1ldk1oOzFvgorevwMdAc5f8H1nT5ssOpg9~~O4t205HKumg10Pb-Ed4PNdZdaH7r-HjBt3P7e7QSra3yl21XsbrInoWHLZ5AAjv2UtUioOavYIW3AMJdhlSZZD39Qcg8bG2s4McOh-Z0462jcvlvoB0~4Exm3yj6rZydJgOW1N0hh0rC1v-BrtQurdbvejFw2i-AsrvsfPsjKkZige2Z~g7c~Ixf6IpzTIJ4vDkrenFeeN58JWe3s4X4fsCt-TujINy-k5p-W-6Ze5ZSD7of0gnnRX96VoYQ24ho38upzfAEVvMtDcSKWMg__"
        )
    }
}