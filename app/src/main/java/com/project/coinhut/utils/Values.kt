package com.project.coinhut.utils

import com.project.coinhut.R

val p1 = PriceRate(
    priceValue = 27815.969173694106,
    timeStamp = 1653464968001
)

val p2 = PriceRate(
    priceValue = 27832.713672151967,
    timeStamp = 1653465222603
)

val p3 = PriceRate(
    priceValue = 27822.351520371165,
    timeStamp = 1653465621765
)

val p4 = PriceRate(
    priceValue = 27810.810856672357,
    timeStamp = 1653465787260
)

val p5 = PriceRate(
    priceValue = 27879.756573409402,
    timeStamp = 1653466194799
)

val prices = Prices(
    maxPrice = 27879.756573409402,
    minPrice = 27810.810856672357,
    prices = listOf(p1, p2, p3, p4, p5)
)

val t1 = Token(
    id = "bitcoin",
    name = "Bitcoin",
    symbol = "btc",
    current_price = 27719.0,
    imgRes = R.drawable.bitcoin_small,
    holding = 0.0,
    description = "Bitcoin is the first successful internet money based on peer-to-peer technology; whereby no central bank or authority is involved in the transaction and production of the Bitcoin currency. It was created by an anonymous individual/group under the name, Satoshi Nakamoto. The source code is available publicly as an open source project, anybody can look at it and be part of the developmental process.\r\n\r\nBitcoin is changing the way we see money as we speak. The idea was to produce a means of exchange, independent of any central authority, that could be transferred electronically in a secure, verifiable and immutable way. It is a decentralized peer-to-peer internet currency making mobile payment easy, very low transaction fees, protects your identity, and it works anywhere all the time with no central authority and banks.\r\n\r\nBitcoin is designed to have only 21 million BTC ever created, thus making it a deflationary currency. Bitcoin uses the <a href=\"https://www.coingecko.com/en?hashing_algorithm=SHA-256\">SHA-256</a> hashing algorithm with an average transaction confirmation time of 10 minutes. Miners today are mining Bitcoin using ASIC chip dedicated to only mining Bitcoin, and the hash rate has shot up to peta hashes.\r\n\r\nBeing the first successful online cryptography currency, Bitcoin has inspired other alternative currencies such as <a href=\"https://www.coingecko.com/en/coins/litecoin\">Litecoin</a>, <a href=\"https://www.coingecko.com/en/coins/peercoin\">Peercoin</a>, <a href=\"https://www.coingecko.com/en/coins/primecoin\">Primecoin</a>, and so on.\r\n\r\nThe cryptocurrency then took off with the innovation of the turing-complete smart contract by <a href=\"https://www.coingecko.com/en/coins/ethereum\">Ethereum</a> which led to the development of other amazing projects such as <a href=\"https://www.coingecko.com/en/coins/eos\">EOS</a>, <a href=\"https://www.coingecko.com/en/coins/tron\">Tron</a>, and even crypto-collectibles such as <a href=\"https://www.coingecko.com/buzz/ethereum-still-king-dapps-cryptokitties-need-1-billion-on-eos\">CryptoKitties</a>.",
    price_change_percentage_24h = 3.2518,
    total_supply = 21000000.0,
    max_supply = 21000000.0,
    circulating_supply = 19050556.0,
    twitter_followers = 5291383,
    reddit_average_posts_48h = 6.7,
    reddit_average_comments_48h = 816.6,
    reddit_subscribers = 4232924,
)

val t2 = Token(
    id = "ethereum",
    name = "Ethereum",
    symbol = "eth",
    current_price = 1684.98,
    imgRes = R.drawable.ethereum_small,
    holding = 0.0,
    price_change_percentage_24h = 1.84793,
    total_supply = 0.0,
    max_supply = 0.0,
    circulating_supply = 120971849.0,
)

val t3 = Token(
    id = "tether",
    name = "Tether",
    symbol = "usdt",
    current_price = 0.932018,
    imgRes = R.drawable.tether_small,
    holding = 0.0,
    price_change_percentage_24h = -0.06974,
    total_supply = 72537249554.0,
    max_supply = 0.0,
    circulating_supply = 72537249554.0,
)

val tokens = listOf(t1, t2, t3)