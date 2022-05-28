package com.project.coinhut.navigation

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object DetailsScreen : Screens("details_screen")
    object EditHoldingsScreen : Screens("edit_holdings_screen")
    object NewAssetScreen : Screens("new_asset_screen")
}