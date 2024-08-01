package com.aquadevs.jira.presentation.features.main.editPhoto

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aquadevs.jira.R
import com.aquadevs.jira.presentation.common.IconButtonCustom
import com.aquadevs.jira.presentation.common.IconCustom
import com.aquadevs.jira.presentation.common.ImageAsyncCustom
import com.aquadevs.jira.presentation.common.TextCustom
import com.aquadevs.jira.ui.validateTheme

@Composable
fun EditPhotoScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        IconButtonCustom(
            iconDR = R.drawable.icon_arrow_back,
            iconColor = validateTheme().primary,
            iconSize = 26,
            modifier = Modifier.padding(10.dp).align(Alignment.TopStart)
        ) {
            navController.popBackStack()
        }
        ImageAsyncCustom(
            urlPicture = "https://s3-alpha-sig.figma.com/img/af22/e37f/c59a84890081e9133704fdb55b8401e3?Expires=1723420800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=lKGdBelAq2GU04i92kHOk1KyeohtpJi1ldk1oOzFvgorevwMdAc5f8H1nT5ssOpg9~~O4t205HKumg10Pb-Ed4PNdZdaH7r-HjBt3P7e7QSra3yl21XsbrInoWHLZ5AAjv2UtUioOavYIW3AMJdhlSZZD39Qcg8bG2s4McOh-Z0462jcvlvoB0~4Exm3yj6rZydJgOW1N0hh0rC1v-BrtQurdbvejFw2i-AsrvsfPsjKkZige2Z~g7c~Ixf6IpzTIJ4vDkrenFeeN58JWe3s4X4fsCt-TujINy-k5p-W-6Ze5ZSD7of0gnnRX96VoYQ24ho38upzfAEVvMtDcSKWMg__",
            modifier = Modifier
                .clip(CircleShape)
                .size(300.dp)
                .padding(horizontal = 10.dp)
                .align(Alignment.Center)
        )
        MyButtonsAction(modifier = Modifier.align(Alignment.BottomEnd))
    }
}

@Composable
fun MyButtonsAction(modifier: Modifier) {
    Column(modifier = modifier) {
        Divider(
            modifier = Modifier
                .height(1.dp)
                .background(validateTheme().secondary)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyItem(icon = R.drawable.icon_edit, text = R.string.editor)
            MyItem(icon = R.drawable.icon_photo_camera, text = R.string.takePhoto)
            MyItem(icon = R.drawable.icon_gallery_thumbnail, text = R.string.galery)
            MyItem(icon = R.drawable.icon_delete, text = R.string.delete)
        }
    }
}

@Composable
private fun MyItem(
    @DrawableRes icon: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Box(
        Modifier
            .clip(CircleShape)
            .clickable {

            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            IconCustom(
                iconColor = Color.White,
                iconSize = 24,
                iconDR = icon
            )
            TextCustom(
                text = stringResource(id = text),
                color = Color.White,
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }
}