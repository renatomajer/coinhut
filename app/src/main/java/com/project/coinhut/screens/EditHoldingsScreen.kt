package com.project.coinhut.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.coinhut.R
import com.project.coinhut.components.TopBarLogoName
import com.project.coinhut.ui.theme.Typography
import com.project.coinhut.utils.Token
import com.project.coinhut.utils.t1
import kotlinx.coroutines.launch


@Composable
fun EditHoldingsScreen(
    modifier: Modifier = Modifier,
    assetId: String,
    navController: NavController
) {

    val asset: Token = t1 // get token from API and value from database

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    var value by remember { mutableStateOf(value = "") }
    var showClearIcon by remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier,
        topBar = {
            TopBarLogoName(
                token = asset,
                // if the asset is added in the database, the backStack should be emptied,
                // otherwise the we should return to add new asset screen (pop the backstack)
                onIconButtonClick = { navController.popBackStack() })
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.small_spacing),
                end = dimensionResource(id = R.dimen.small_spacing),
                bottom = dimensionResource(id = R.dimen.small_spacing)
            )
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.current_holdings) + ":",
                    style = Typography.h6,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.edit_holdings_section_top_padding)
                        )
                        .fillMaxWidth()
                )
            }

            item {
                Text(
                    text = asset.holding.toString() + " " + asset.symbol.uppercase(),
                    style = Typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.edit_holdings_section_value_top_padding),
                            bottom = dimensionResource(id = R.dimen.edit_holdings_section_value_top_padding)
                        )
                        .fillMaxWidth()
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.new_holdings) + ":",
                    style = Typography.h6,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.edit_holdings_section_top_padding)
                        )
                        .fillMaxWidth()
                )
            }



            item {
                if (value.isEmpty()) {
                    showClearIcon = false
                } else if (value.isNotEmpty()) {
                    showClearIcon = true
                }

                TextField(
                    value = value,
                    onValueChange = { value = it },
                    singleLine = true,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.edit_holdings_section_value_top_padding),
                            bottom = dimensionResource(id = R.dimen.edit_holdings_section_value_top_padding)
                        )
                        .background(color = Color(0xFFEAEAEB), shape = RoundedCornerShape(5.dp))
                        .fillMaxWidth(),
                    textStyle = Typography.body1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //new
                    trailingIcon = {
                        if (showClearIcon) {
                            IconButton(onClick = { value = "" }) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    tint = Color.Black,
                                    contentDescription = "Clear Icon"
                                )
                            }
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.Black,
                        unfocusedLabelColor = Color.Transparent
                    ),
                    label = {
                        Text(
                            text = stringResource(id = R.string.input_field),
                            style = Typography.body1,
                            color = Color.Gray
                        )
                    }
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.edit_holdings_row_padding_start_end),
                            end = dimensionResource(id = R.dimen.edit_holdings_row_padding_start_end),
                            top = dimensionResource(id = R.dimen.edit_holdings_row_top_padding)
                        )
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        ),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        ),
                        onClick = {
                            /*TODO add on click logic*/
                            try {
                                val newHolding = value.replace(",", ".")

                                if (newHolding.toDouble() < 0.0) throw NumberFormatException()

                                asset.holding = newHolding.toDouble()
                            } catch (exception: NumberFormatException) {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        "Wrong number format!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.done))
                    }
                }
            }
        }
    }
}


//@Composable
//@Preview
//fun EditHoldingsScreenPreview() {
//    val t1 = Token(
//        id = "bitcoin",
//        name = "Bitcoin",
//        symbol = "btc",
//        price = 27719.0,
//        image = R.drawable.bitcoin_small,
//        holding = 15.215
//    )
//
//    InputField()
//}