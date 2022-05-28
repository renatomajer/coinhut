package com.project.coinhut.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.coinhut.screens.DetailsScreen
import com.project.coinhut.screens.EditHoldingsScreen
import com.project.coinhut.screens.MainScreen
import com.project.coinhut.screens.NewAssetScreen
import com.project.coinhut.utils.t1


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {

        composable(route = Screens.MainScreen.route) {
            MainScreen(
                assets = listOf(t1, t1, t1),
                tokens = listOf(t1, t1, t1),
                navController = navController
            )
        }

        composable(route = Screens.NewAssetScreen.route) {
            NewAssetScreen(assets = listOf(t1, t1, t1), navController = navController)
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