package com.project.coinhut.utils

import com.project.coinhut.R


data class Prices(
    val maxPrice: Double,
    val prices: List<PriceRate>
)


data class PriceRate(
    val timeStamp: Long,
    val priceValue: Double
)