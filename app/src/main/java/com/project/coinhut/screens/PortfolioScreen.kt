package com.project.coinhut.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.project.coinhut.R
import com.project.coinhut.components.AssetCard
import com.project.coinhut.navigation.Screens
import com.project.coinhut.ui.theme.Typography
import com.project.coinhut.utils.Token
import com.project.coinhut.viewmodel.PortfolioScreenViewModel
import org.koin.androidx.compose.viewModel


@Composable
fun PortfolioScreen(
    modifier: Modifier = Modifier,
    onAddNewAssetClick: () -> Unit = {},
    navController: NavController
) {

    val portfolioScreenViewModel by viewModel<PortfolioScreenViewModel>()

    val assets: List<Token> by portfolioScreenViewModel.getAssets().collectAsState(initial = listOf())// list of tokens in possession

    Scaffold(
        modifier = modifier,
        floatingActionButton = { AddNewAssetButton { navController.navigate(Screens.NewAssetScreen.route) } },
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = Color.Transparent
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = dimensionResource(id = R.dimen.small_spacing)
                )
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.portfolio_screen_header_bottom_padding)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.asset),
                        style = Typography.subtitle1
                    )

                    Text(
                        text = stringResource(id = R.string.price),
                        style = Typography.subtitle1
                    )

                    Text(
                        text = stringResource(id = R.string.holdings),
                        style = Typography.subtitle1
                    )
                }
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(Dp(1f))
                        .fillMaxWidth()
                        .background(Color.Gray)
                )
            }

            items(assets.size) {
                AssetCard(
                    token = assets[it],
                    onAssetClick = { navController.navigate(Screens.DetailsScreen.route + "/${assets[it].id}") },
                    onHoldingClick = { navController.navigate(Screens.EditHoldingsScreen.route + "/${assets[it].id}") }
                )
            }
        }
    }

}


@Composable
fun AddNewAssetButton(
    onAddNewAssetClick: () -> Unit = {}
) {
    ExtendedFloatingActionButton(
        text = {
            Text(
                text = stringResource(id = R.string.add_new_asset),
                style = Typography.button
            )
        },
        onClick = { onAddNewAssetClick() },
        icon = { Icon(Icons.Filled.Add, contentDescription = null, tint = Color.White) },
        backgroundColor = Color.Blue,
        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.add_new_asset_button_bottom_padding))
    )
}


//@Preview
//@Composable
//fun PortfolioScreenPreview() {
//    val t1 = Token(
//        id = "bitcoin",
//        name = "Bitcoin",
//        symbol = "btc",
//        price = 27719.0,
//        image = R.drawable.bitcoin_small,
//        holding = 0.5
//    )
//
//    PortfolioScreen(assets = listOf(t1, t1, t1))
//}



