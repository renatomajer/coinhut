package com.project.coinhut.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.project.coinhut.utils.Token
import com.project.coinhut.R
import com.project.coinhut.ui.theme.Typography


//TODO: on click functions on all screens and components forwarding???
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var home by remember { mutableStateOf(true) }

    Scaffold(
        topBar = { MainScreenTopBar() },
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                BottomNavigationItem(
                    selected = true,
                    onClick = {
                        if (!home) {
                            home = home.not()
                        }
                    },
                    icon = {
                        if (home) {
                            Icon(
                                Icons.Filled.Home, contentDescription = null,
                                tint = Color.Black
                            )
                        } else {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = null,
                                tint = Color.LightGray
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.home),
                            style = Typography.overline,
                            color = if (home) Color.Black else Color.LightGray
                        )
                    }
                )

                BottomNavigationItem(
                    selected = false,
                    onClick = {
                        if (home) {
                            home = home.not()
                        }
                    },
                    icon = {
                        if (!home) {
                            Icon(
                                painterResource(id = R.drawable.baseline_account_balance_wallet_black_48),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        } else {
                            Icon(
                                painterResource(id = R.drawable.baseline_account_balance_wallet_white_48),
                                contentDescription = null,
                                tint = Color.LightGray
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.portfolio),
                            color = if (home) Color.LightGray else Color.Black
                        )
                    }
                )
            }
        }
    ) {
        if (home) {
            HomeScreen(
                modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
                navController = navController
            )
        } else {
            PortfolioScreen(
                modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
                navController = navController
            )
        }

    }

}


@Composable
fun MainScreenTopBar() {
    TopAppBar(
        backgroundColor = Color.Blue
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = stringResource(id = R.string.app_name),
                style = Typography.h4
            )

        }
    }
}


//@Composable
//@Preview
//fun MainScreenPreview() {
//    val t1 = Token(
//        id = "bitcoin",
//        name = "Bitcoin",
//        symbol = "btc",
//        price = 27719.0,
//        image = R.drawable.bitcoin_small,
//        holding = 0.5
//    )
//
//    MainScreen(assets = listOf(t1, t1, t1), tokens = listOf(t1, t1, t1))
//}