package com.project.coinhut.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.project.coinhut.R
import com.project.coinhut.ui.theme.Typography
import com.project.coinhut.utils.Token


@Composable
fun TokenCard(
    modifier: Modifier = Modifier,
    onTokenCardClick: () -> Unit = {},
    token: Token
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .clickable { onTokenCardClick() }
                .fillMaxWidth()
                .height(
                    dimensionResource(
                        id = R.dimen.token_card_height
                    )
                )
                .padding(
                    start = dimensionResource(id = R.dimen.small_spacing),
                    end = dimensionResource(id = R.dimen.small_spacing)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Token
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = token.image),
                    contentDescription = null
                )

                Column(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.token_card_name_start_padding),
                        end = dimensionResource(id = R.dimen.token_card_name_end_padding)
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

            // Price
            Text(
                text = ((token.price * 100).toInt() / 100.0).toString() + "€",
                style = Typography.h6
            )
        }

        Spacer(
            modifier = Modifier
                .height(Dp(0.5f))
                .fillMaxWidth()
                .background(Color.Gray)
        )
    }
}


@Preview
@Composable
fun TokenCardPreview() {
    val t1 = Token(
        id = "bitcoin",
        name = "Bitcoin",
        symbol = "btc",
        price = 27719.0,
        image = R.drawable.bitcoin_small
    )

    TokenCard(token = t1)
}