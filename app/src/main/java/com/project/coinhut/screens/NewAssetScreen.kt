package com.project.coinhut.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.project.coinhut.components.NewAssetCard
import com.project.coinhut.utils.Token
import com.project.coinhut.R
import com.project.coinhut.navigation.Screens
import com.project.coinhut.ui.theme.Typography
import com.project.coinhut.viewmodel.NewAssetScreenViewModel
import org.koin.androidx.compose.viewModel


@Composable
fun NewAssetScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val newAssetScreenViewModel by viewModel<NewAssetScreenViewModel>()

    val assets: List<Token> by newAssetScreenViewModel.getNewAssets().collectAsState(initial = listOf())

    Scaffold(
        topBar = { NewAssetScreenTopBar( onIconButtonClick = { navController.popBackStack() }) },
        modifier = modifier,

        ) {

        LazyColumn(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.small_spacing),
                end = dimensionResource(id = R.dimen.small_spacing),
                bottom = dimensionResource(id = R.dimen.small_spacing)
            )
        ) {

            items(assets.size) {
                NewAssetCard(
                    token = assets[it],
                    onAddButtonClick = { navController.navigate(Screens.EditHoldingsScreen.route + "/${assets[it].id}") },
                    onTokenButtonClick = { navController.navigate(Screens.DetailsScreen.route + "/${assets[it].id}") }
                )
            }
        }

    }
}


@Composable
fun NewAssetScreenTopBar(
    onIconButtonClick: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Color.Blue
    ) {
        IconButton(onClick = { onIconButtonClick() }) {
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White
            )

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = dimensionResource(id = R.dimen.new_asset_screen_title_end_padding)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.add_new_asset),
                style = Typography.h6,
                color = Color.White
            )
        }
    }
}


//@Composable
//@Preview
//fun NewAssetScreenPreview() {
//    val t1 = Token(
//        id = "bitcoin",
//        name = "Bitcoin",
//        symbol = "btc",
//        price = 27719.0,
//        image = R.drawable.bitcoin_small
//    )
//
//    NewAssetScreen(assets = listOf(t1, t1, t1, t1))
//}