package com.project.coinhut.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
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
                    start = dimensionResource(id = R.dimen.token_card_content_start_padding),
                    end = dimensionResource(id = R.dimen.token_card_content_end_padding)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Token
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = token.image.replace(
                            "/large/",
                            "/small/"
                        )
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.image_size))
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
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = stringResource(R.string.eur) + " " + ((token.current_price * 100).toInt() / 100.0).toString(),
                    style = Typography.h6,
                    fontWeight = FontWeight.Medium
                )

                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = if (token.price_change_percentage_24h > 0.0) painterResource(id = R.drawable.baseline_north_east_black_36) else painterResource(
                            id = R.drawable.baseline_south_east_black_36
                        ),
                        contentDescription = null,
                        tint = if (token.price_change_percentage_24h > 0.0) Color.Green else Color.Red,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.micro_spacing))
                    )

                    Text(
                        text = ("%.2f".format(token.price_change_percentage_24h) + "%"),
                        style = Typography.h6,
                        fontWeight = FontWeight.Normal,
                        color = if (token.price_change_percentage_24h > 0.0) Color.Green else Color.Red
                    )
                }
            }
        }

//        Spacer(
//            modifier = Modifier
//                .height(Dp(0.5f))
//                .fillMaxWidth()
//                .background(Color.Gray)
//        )
    }
}


@Preview
@Composable
fun TokenCardPreview() {
    val t1 = Token(
        id = "bitcoin",
        name = "Bitcoin",
        symbol = "btc",
        current_price = 27719.0,
        imgRes = R.drawable.bitcoin_small
    )

    TokenCard(token = t1)
}