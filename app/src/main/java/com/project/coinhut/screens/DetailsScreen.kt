package com.project.coinhut.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.coinhut.R
import com.project.coinhut.components.LineChart
import com.project.coinhut.components.TopBarLogoName
import com.project.coinhut.ui.theme.Typography
import com.project.coinhut.utils.Prices
import com.project.coinhut.utils.Token
import com.project.coinhut.viewmodel.DetailsScreenViewModel
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*


@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    tokenId: String,
    navController: NavController
) {

    val detailsScreenViewModel by viewModel<DetailsScreenViewModel> { parametersOf(tokenId) }

    val prices: Prices by detailsScreenViewModel.getPrices(tokenId)
        .collectAsState(
            initial = Prices(
                0.0,
                0.0,
                listOf()
            )
        ) // prices need to be loaded from the API

    val token by detailsScreenViewModel.getToken(tokenId)
        .collectAsState(initial = Token()) // token needs to be loaded from the API

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarLogoName(
                token = token,
                onIconButtonClick = { navController.popBackStack() })
        }
    ) {

        LazyColumn(
            contentPadding = PaddingValues(
                top = dimensionResource(id = R.dimen.details_screen_content_top_padding),
                start = dimensionResource(id = R.dimen.details_screen_content_start_end_padding),
                end = dimensionResource(id = R.dimen.details_screen_content_start_end_padding),
                bottom = dimensionResource(id = R.dimen.details_screen_content_top_padding)
            )
        ) {

            // Overview
            item {
                Text(
                    text = tokenId.replaceFirstChar { it.uppercase() } + " " + stringResource(id = R.string.overview),
                    style = Typography.h5
                )
            }

            // Price
            item {
                Row(
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.details_screen_content_top_padding),
                            start = dimensionResource(id = R.dimen.details_screen_price_start_padding),
                            bottom = dimensionResource(id = R.dimen.details_screen_price_bottom_padding),
                            end = dimensionResource(id = R.dimen.details_screen_price_start_padding)
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {

                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = stringResource(id = R.string.price) + ": ",
                            color = Color.LightGray
                        )

                        Text(
                            text = token.current_price.toString() + " €",
                            style = Typography.h6,
                            fontSize = 18.sp
                        )
                    }


                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(text = "24h: ", color = Color.LightGray)

                        ChangePercentage(token = token)
                    }
                }
            }

            // Chart
            item {
                LineChart(prices = prices)
            }

            // About
            item {
                Row(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.details_screen_section_title_top_padding)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Info, contentDescription = null, tint = Color.LightGray)

                    Text(
                        text = stringResource(id = R.string.about) + " " + token.name,
                        style = Typography.h6,
                        color = Color.LightGray
                    )
                }
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(Dp(1f))
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
            }

            item {
                // get only text from description given as HTML
                Text(
                    text = if (token.description.length > 1) Regex("\\<.*?>").replace(
                        token.description,
                        ""
                    ) else stringResource(
                        id = R.string.no_description
                    ),
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.small_spacing),
                        bottom = dimensionResource(id = R.dimen.small_spacing)
                    )
                )
            }

            // Supply
            item {
                Row(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.details_screen_section_title_top_padding)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = null,
                        tint = Color.LightGray
                    )

                    Text(
                        text = stringResource(id = R.string.supply),
                        style = Typography.h6,
                        color = Color.LightGray
                    )
                }
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(Dp(1f))
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.total_supply) + ": "
                            + if (token.total_supply != null) "%f".format(token.total_supply)
                        .replace(",", ".") else stringResource(
                        id = R.string.unknown
                    ),
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.small_spacing)
                    )
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.max_supply) + ": " + if (token.max_supply != null) "%f".format(
                        token.max_supply
                    )
                        .replace(",", ".") else stringResource(
                        id = R.string.unknown
                    )
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.circulating_supply) + ": " + if (token.circulating_supply != null) "%f".format(
                        token.circulating_supply
                    )
                        .replace(",", ".") else stringResource(
                        id = R.string.unknown
                    ),
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.small_spacing))
                )
            }

            // Community
            item {
                Row(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.details_screen_section_title_top_padding)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Person, contentDescription = null, tint = Color.LightGray)

                    Text(
                        text = stringResource(id = R.string.community),
                        style = Typography.h6,
                        color = Color.LightGray
                    )
                }
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(Dp(1f))
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.facebook_likes) + ": " + if (token.facebook_likes != null) token.facebook_likes else stringResource(
                        id = R.string.unknown
                    ),
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_spacing))
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.twitter_followers) + ": " + if (token.twitter_followers != null) token.twitter_followers else stringResource(
                        id = R.string.unknown
                    )
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.reddit_average_posts_48h) + ": " + if (token.reddit_average_posts_48h != null) token.reddit_average_posts_48h else stringResource(
                        id = R.string.unknown
                    )
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.reddit_average_comments_48h) + ": " + if (token.reddit_average_comments_48h != null) token.reddit_average_comments_48h else stringResource(
                        id = R.string.unknown
                    )
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.reddit_subscribers) + ": " + if (token.reddit_subscribers != null) token.reddit_subscribers else stringResource(
                        id = R.string.unknown
                    )
                )
            }
        }
    }
}


@Composable
fun ChangePercentage(
    token: Token
) {
    Surface(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.details_screen_surface_corner)),
        modifier = Modifier.width(dimensionResource(id = R.dimen.details_screen_surface_percentage_width)),
        color = if (token.price_change_percentage_24h > 0.0) Color.Green else Color.Red
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (token.price_change_percentage_24h > 0.0) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_north_east_black_36),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.micro_spacing))
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_south_east_black_36),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.micro_spacing))
                )
            }

            Text(
                text = ((token.price_change_percentage_24h * 1000).toInt() / 1000.0).toString() + "%",
                color = Color.White
            )
        }
    }
}


//@Composable
//@Preview
//fun DetailsScreenPreview() {
//    DetailsScreen(tokenId = "bitcoin")
//}