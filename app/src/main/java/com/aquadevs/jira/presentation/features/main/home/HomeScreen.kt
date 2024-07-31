package com.aquadevs.jira.presentation.features.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aquadevs.jira.R
import com.aquadevs.jira.presentation.common.ButtonCustom
import com.aquadevs.jira.presentation.common.ButtonImageCustom
import com.aquadevs.jira.presentation.common.IconButtonCustom
import com.aquadevs.jira.presentation.common.IconCustom
import com.aquadevs.jira.presentation.common.ImageAsyncCustom
import com.aquadevs.jira.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.jira.presentation.common.TextCustom
import com.aquadevs.jira.presentation.model.BoardDto
import com.aquadevs.jira.presentation.model.PersonDto
import com.aquadevs.jira.ui.validateTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            MyHeader()
            MyBody(modifier = Modifier.weight(1f))
        }

        FloatingActionButton(
            onClick = {

            },
            containerColor = validateTheme().primary,
            modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_round_add),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun MyBody(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Column(modifier = modifier.fillMaxSize()) {
        TextCustom(
            text = stringResource(id = R.string.boards),
            fontSize = 20,
            modifier = Modifier.fillMaxWidth()
        )
        val state = rememberLazyStaggeredGridState()
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp,
            //contentPadding = PaddingValues(16.dp),
            state = state,
            modifier = Modifier.background(Color.Transparent)
        ) {
            items(homeViewModel.listBoard.sortedBy { it.idBoard }, key = { it.idBoard }) { item ->
                MyItem(item)
            }
        }
    }
}

@Composable
fun MyItem(boardDto: BoardDto, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = R.drawable.item_table),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Box(modifier = Modifier
                .clip(CircleShape)
                .background(Color(0XFFF4F9FF))) {
                Image(
                    painter = painterResource(id = boardDto.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            TextCustom(text = boardDto.nameItem, color = Color.Black)
            TextCustom(text = boardDto.codItem, color = validateTheme().secondary)
            TextCustom(
                text = boardDto.stateDescription,
                modifier = Modifier.background(boardDto.colorState),
                color = Color.Black
            )
        }
    }
}

@Composable
private fun MyHeader(homeViewModel: HomeViewModel = hiltViewModel()) {
    val personDto by homeViewModel.personDto.observeAsState(initial = PersonDto())
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageAsyncCustom(
                urlPicture = personDto.urlProfile,
                size = 50
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TextCustom(
                    text = personDto.userName,
                    fontSize = 22,
                    overflow = TextOverflow.Ellipsis
                )
                TextCustom(
                    text = personDto.companyName,
                    overflow = TextOverflow.Ellipsis,
                    color = validateTheme().secondary
                )
            }
            IconButtonCustom(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, color = validateTheme().secondary.copy(0.3f), CircleShape),
                iconDR = R.drawable.icon_notification,
                iconSize = 50,
                iconColor = validateTheme().primary
            ) {

            }
        }

        OutlinedTextFieldCustom(
            value = "",
            label = stringResource(id = R.string.searchInBoards),
            maxLine = 1,
            trailingIcon = R.drawable.icon_tune,
            leadingIcon = R.drawable.icon_search,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            sizeIcon = 25,
        ) {

        }
    }
}
