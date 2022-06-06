package com.project.coinhut.utils

import androidx.compose.ui.res.stringResource
import com.project.coinhut.R
import kotlinx.serialization.Serializable

// TODO: edit all the nullable values
@Serializable
data class Token(
    var id: String = "",
    var symbol: String = "",
    var name: String = "",
    var description: String = "",
    var current_price: Double = 0.0, // token price in eur
    var imgRes: Int = R.drawable.bitcoin_small,
    var image: String = "",
    var holding: Double = 0.0, // quantity of the token in possession
    var price_change_percentage_24h: Double = 0.0,
    var total_supply: Double? = 0.0, // TODO all of the supply values need to be double or null
    var max_supply: Double? = 0.0,
    var circulating_supply: Double? = 0.0,
    var facebook_likes: Long? = 0L,
    var twitter_followers: Long? = 0L,
    var reddit_average_posts_48h: Double? = 0.0,
    var reddit_average_comments_48h: Double? = 0.0,
    var reddit_subscribers: Long? = 0L
)


@Serializable
data class TokenResponse(
    var id: String = "",
    var symbol: String = "",
    var name: String = "",
    var description: Description = Description(),
    var market_data: MarketData = MarketData(),
    var image: Image = Image(),
    var community_data: CommunityData = CommunityData()
) {
    fun toToken(): Token {
        return Token(
            id = id,
            symbol = symbol,
            name = name,
            description = description.en,
            current_price = market_data.current_price.eur,
            image = image.small,
            price_change_percentage_24h = market_data.price_change_percentage_24h,
            total_supply = market_data.total_supply,
            max_supply = market_data.max_supply,
            circulating_supply = market_data.circulating_supply,
            facebook_likes = community_data.facebook_likes,
            twitter_followers = community_data.twitter_followers,
            reddit_average_posts_48h = community_data.reddit_average_posts_48h,
            reddit_average_comments_48h = community_data.reddit_average_comments_48h,
            reddit_subscribers = community_data.reddit_subscribers
        )
    }
}


@Serializable
data class Description(
    var en: String = ""
)


@Serializable
data class Image(
    var small: String = ""
)


@Serializable
data class MarketData(
    var current_price: CurrentPrice = CurrentPrice(),
    var price_change_percentage_24h: Double = 0.0,
    var total_supply: Double? = 0.0,
    var max_supply: Double? = 0.0,
    var circulating_supply: Double? = 0.0,
)


@Serializable
data class CurrentPrice(
    var eur: Double = 0.0
)


@Serializable
data class CommunityData(
    var facebook_likes: Long? = 0L,
    var twitter_followers: Long? = 0L,
    var reddit_average_posts_48h: Double? = 0.0,
    var reddit_average_comments_48h: Double? = 0.0,
    var reddit_subscribers: Long? = 0L,
)