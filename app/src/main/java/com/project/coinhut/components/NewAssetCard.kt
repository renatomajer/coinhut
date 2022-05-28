package com.project.coinhut.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.project.coinhut.R
import com.project.coinhut.ui.theme.Typography
import com.project.coinhut.utils.Token


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewAssetCard(
    modifier: Modifier = Modifier,
    token: Token,
    onAddButtonClick: () -> Unit = {},
    onTokenButtonClick: () -> Unit = {}
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    dimensionResource(
                        id = R.dimen.new_asset_card_height
                    )
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Token
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { onTokenButtonClick() }
            ) {
                Image(
                    painter = painterResource(id = token.image),
                    contentDescription = null
                )

                Column(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.new_asset_card_name_start_padding),
                        end = dimensionResource(id = R.dimen.new_asset_card_name_end_padding)
                    )
                ) {
                    Text(
                        text = token.name,
                        style = Typography.h6,
                    )

                    Text(
                        text = token.symbol.uppercase(),
                        style = Typography.subtitle1
                    )
                }
            }

            // Add
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(dimensionResource(id = R.dimen.new_asset_card_add_width))
                    .clickable { onAddButtonClick() }
            ) {
                Text(
                    text = stringResource(id = R.string.add),
                    style = Typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .width(dimensionResource(id = R.dimen.new_asset_card_add_border_width))
                )
            }
        }

        Spacer(
            modifier = Modifier
                .height(Dp(0.5f))
                .fillMaxWidth()
                .background(Color.Gray)
        )
    }

}


@Composable
@Preview
fun NewAssetCardPreview() {
    val t1 = Token(
        id = "bitcoin",
        name = "Bitcoin",
        symbol = "btc",
        price = 27719.0,
        image = R.drawable.bitcoin_small
    )

    NewAssetCard(token = t1)
}