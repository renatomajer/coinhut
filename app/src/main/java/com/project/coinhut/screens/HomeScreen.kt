package com.project.coinhut.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
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
import com.project.coinhut.components.TokenCard
import com.project.coinhut.navigation.Screens
import com.project.coinhut.ui.theme.Typography
import com.project.coinhut.utils.Token
import com.project.coinhut.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val homeScreenViewModel by viewModel<HomeScreenViewModel>()

    val tokens: List<Token> by homeScreenViewModel.getTokens().collectAsState(initial = listOf())

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                bottom = dimensionResource(id = R.dimen.small_spacing)
            )
    ) {

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimensionResource(id = R.dimen.home_screen_header_bottom_padding),
                        top = dimensionResource(id = R.dimen.home_screen_header_top_padding),
                        start = dimensionResource(id = R.dimen.home_screen_header_start_padding),
                        end = dimensionResource(id = R.dimen.home_screen_header_end_padding)
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.token),
                    style = Typography.subtitle1
                )

                Text(
                    text = stringResource(id = R.string.price),
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

        items(tokens.size) {
            TokenCard(
                token = tokens[it],
                onTokenCardClick = { navController.navigate(Screens.DetailsScreen.route + "/${tokens[it].id}") }
            )
        }

    }

}

//
//@Composable
//@Preview
//fun HomeScreenPreview() {
//    val t1 = Token(
//        id = "bitcoin",
//        name = "Bitcoin",
//        symbol = "btc",
//        price = 27719.0,
//        image = R.drawable.bitcoin_small
//    )
//
//    HomeScreen(tokens = listOf(t1, t1, t1))
//}