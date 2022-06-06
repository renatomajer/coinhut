package com.project.coinhut.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.coinhut.R
import com.project.coinhut.screens.*
import com.project.coinhut.utils.t1


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {

        composable(route = Screens.MainScreen.route) {
            MainScreen(
                navController = navController
            )
        }

        composable(route = Screens.NewAssetScreen.route) {
            NewAssetScreen(navController = navController)
        }

        composable(
            route = Screens.DetailsScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("id")
                ?.let { DetailsScreen(tokenId = it, navController = navController) }
        }

        composable(
            route = Screens.EditHoldingsScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("id")?.let {
                EditHoldingsScreen(
                    assetId = it,
                    navController = navController
                )
            }
        }


    }
}


//@Composable
//fun BottomBarNavgationGraph(
//    navController: NavHostController,
//    bottomBarNavHostController: NavHostController
//) {
//
//    NavHost(navController = bottomBarNavHostController, startDestination = BottomBarScreen.Home.route) {
//        composable(BottomBarScreen.Home.route) {
//            HomeScreen(navController = navController)
//        }
//
//        composable(BottomBarScreen.Portfolio.route) {
//            PortfolioScreen(navController = navController)
//        }
//    }
//}
//
//
//sealed class BottomBarScreen(
//    val route: String,
//    val title: String,
//    val icon: Int,
//) {
//    object Home : BottomBarScreen(
//        route = "home",
//        title = "Home",
//        icon = R.drawable.baseline_home_black_48
//    )
//
//    object Portfolio : BottomBarScreen(
//        route = "portfolio",
//        title = "Portfolio",
//        icon = R.drawable.baseline_account_balance_wallet_black_48
//    )
//}