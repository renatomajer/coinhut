package com.project.coinhut.utils

import kotlinx.serialization.Serializable
import kotlin.math.min


data class Prices(
    val maxPrice: Double,
    val minPrice: Double,
    val prices: List<PriceRate>
)


data class PriceRate(
    val timeStamp: Long,
    val priceValue: Double
)


@Serializable
data class PricesResponse(
    var prices: List<List<Double>>
) {
    fun toPrices(): Prices {
        var reduced = mutableListOf<List<Double>>()

        for (i in prices.indices) {
            if (i % 3 == 0) {
                reduced.add(prices[i])
            }
        }

        val prices: MutableList<PriceRate> = mutableListOf()
        var maxPrice = 0.0
        var minPrice = reduced.first()[1]

        for (elem in reduced) {

            if (elem[1] > maxPrice) {
                maxPrice = elem[1]
            }

            if (elem[1] < minPrice) {
                minPrice = elem[1]
            }

            val priceRate = PriceRate(elem[0].toLong(), elem[1])

            prices.add(priceRate)
        }

        return Prices(
            maxPrice = maxPrice,
            minPrice = minPrice,
            prices = prices
        )
    }
}