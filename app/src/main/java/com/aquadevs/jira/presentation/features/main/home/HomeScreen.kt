package com.aquadevs.jira.presentation.features.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aquadevs.jira.R
import com.aquadevs.jira.core.function.General.DialogProgress
import com.aquadevs.jira.presentation.common.ButtonCustom
import com.aquadevs.jira.presentation.common.DialogCustom
import com.aquadevs.jira.presentation.common.IconButtonCustom
import com.aquadevs.jira.presentation.common.ImageAsyncCustom
import com.aquadevs.jira.presentation.common.OutlinedButtonCustom
import com.aquadevs.jira.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.jira.presentation.common.TextCustom
import com.aquadevs.jira.presentation.common.ToastCustom
import com.aquadevs.jira.presentation.model.BoardDto
import com.aquadevs.jira.presentation.model.PersonDto
import com.aquadevs.jira.presentation.navigation.MainRoute
import com.aquadevs.jira.ui.validateTheme

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            MyHeader(navController = navController)
            MyBody(modifier = Modifier.weight(1f))
        }

        FloatingActionButton(
            onClick = {
                navController.navigate(MainRoute.NavNewProjectScreen.route)
            },
            containerColor = validateTheme().primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_round_add),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
        MyDialogCustom()
    }
}

@Composable
private fun MyBody(
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
private fun MyItem(boardDto: BoardDto, modifier: Modifier = Modifier) {
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
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0XFFF4F9FF))
            ) {
                Image(
                    painter = painterResource(id = boardDto.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(10.dp)
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
private fun MyHeader(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val personDto by homeViewModel.personDto.observeAsState(initial = PersonDto())
    val search by homeViewModel.search.observeAsState(initial = "")
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageAsyncCustom(
                urlPicture = personDto.urlProfile,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .clickable {
                        navController.navigate(MainRoute.NavProfileScreen.route)
                    }
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
                iconColor = validateTheme().primary,
                modifierIcon = Modifier.padding(15.dp)
            ) {

            }
        }

        OutlinedTextFieldCustom(
            value = search,
            label = stringResource(id = R.string.searchInBoards),
            maxLine = 1,
            trailingIcon = R.drawable.icon_tune,
            leadingIcon = R.drawable.icon_search,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            sizeIcon = 25,
            onClickTrailingIcon = {
                homeViewModel.detectAction(idAction = 1)
            }
        ) {
            homeViewModel.searchBoard(it)
        }
    }
}

@Composable
private fun AdvancedSearch(homeViewModel: HomeViewModel = hiltViewModel()) {
    DialogCustom(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 10,
        onDismissRequest = { /*TODO*/ }
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            MyHeaderDialog(homeViewModel)
            MyBodyDialog(homeViewModel)
            MyFooterDialog(homeViewModel)
        }
    }
}

@Composable
private fun MyFooterDialog(homeViewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        ButtonCustom(
            textButton = stringResource(id = R.string.search),
            modifier = Modifier.fillMaxWidth(),
            modifierText = Modifier.padding(vertical = 5.dp)
        ) {
            homeViewModel.detectAction(idAction = 1, bool = false)
        }
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedButtonCustom(
            textButton = stringResource(id = R.string.clean),
            modifier = Modifier.fillMaxWidth(),
            modifierText = Modifier.padding(vertical = 5.dp)
        ) {
            homeViewModel.cleanDialog()
        }
    }
}

@Composable
private fun MyBodyDialog(homeViewModel: HomeViewModel) {
    val projectCode by homeViewModel.projectCode.observeAsState(initial = "")
    val name by homeViewModel.name.observeAsState(initial = "")
    val state by homeViewModel.state.observeAsState(initial = "")
    val category by homeViewModel.category.observeAsState(initial = "")
    val projectIcon by homeViewModel.projectIcon.observeAsState(initial = "")
    val startDate by homeViewModel.startDate.observeAsState(initial = "")
    val endingDate by homeViewModel.endingDate.observeAsState(initial = "")

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextFieldCustom(
            value = projectCode,
            label = stringResource(id = R.string.projectCode),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            homeViewModel.validateHome(projectCode = it)
        }

        OutlinedTextFieldCustom(
            value = name,
            label = stringResource(id = R.string.name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            homeViewModel.validateHome(name = it)
        }

        OutlinedTextFieldCustom(
            value = state,
            label = stringResource(id = R.string.state),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            homeViewModel.validateHome(state = it)
        }

        OutlinedTextFieldCustom(
            value = category,
            label = stringResource(id = R.string.category),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            homeViewModel.validateHome(category = it)
        }

        OutlinedTextFieldCustom(
            value = projectIcon,
            label = stringResource(id = R.string.projectIcon),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            homeViewModel.validateHome(projectIcon = it)
        }

        OutlinedTextFieldCustom(
            value = startDate,
            label = stringResource(id = R.string.startDate),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            homeViewModel.validateHome(startDate = it)
        }

        OutlinedTextFieldCustom(
            value = endingDate,
            label = stringResource(id = R.string.edingDate),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            homeViewModel.validateHome(endingDate = it)
        }
    }
}

@Composable
private fun MyHeaderDialog(homeViewModel: HomeViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextCustom(
            text = stringResource(id = R.string.advanceSearch),
            modifier = Modifier.weight(1f),
            fontSize = 18
        )
        IconButtonCustom(
            iconDR = R.drawable.icon_close,
            iconSize = 22,
            modifier = Modifier.padding(end = 4.dp)
        ) {
            homeViewModel.detectAction(idAction = 1, bool = false)
        }
    }
}

@Composable
private fun MyDialogCustom(homeViewModel: HomeViewModel = hiltViewModel()) {
    val isShow by homeViewModel.showAdvancedSearchDialog.observeAsState(initial = false)
    val messageToast by homeViewModel.messageToast.observeAsState(initial = 0)
    val isLoading by homeViewModel.isLoading.observeAsState(initial = false)
    if (isLoading) DialogProgress()
    if (isShow) AdvancedSearch()
    if (messageToast != 0) {
        ToastCustom(text = stringResource(id = messageToast))
        homeViewModel.detectAction(idAction = 0)
    }
}