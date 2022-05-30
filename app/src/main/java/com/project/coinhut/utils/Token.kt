package com.project.coinhut.utils

// TODO: edit all the nullable values
data class Token(
    var id: String = "",
    var symbol: String = "",
    var name: String = "",
    var description: String = "",
    var price: Double = 0.0, // token price in eur
    var image: Int = 0,
    var holding: Double = 0.0, // quantity of the token in possession
    var price_change_percentage_24h: Double = 0.0,
    var total_supply: Long = 0L, // TODO all of the supply values need to be double or null
    var max_supply: Long = 0L,
    var circulating_supply: Long = 0L,
    var facebook_likes: Long? = 0L,
    var twitter_followers: Long? = 0L,
    var reddit_average_posts_48h: Double? = 0.0,
    var reddit_average_comments_48h: Double? = 0.0,
    var reddit_subscribers: Long? = 0L
)
