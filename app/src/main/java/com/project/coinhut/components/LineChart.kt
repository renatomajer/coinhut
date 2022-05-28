package com.project.coinhut.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.coinhut.R
import com.project.coinhut.utils.PriceRate
import com.project.coinhut.utils.Prices


@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    prices: Prices
) {

    if (prices.prices.isEmpty()) return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.line_chart_card_height))
            .padding(dimensionResource(id = R.dimen.line_chart_card_padding))
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.line_chart_column_padding))
                .wrapContentSize(align = Alignment.BottomStart)
        ) {
            Canvas(modifier = modifier.fillMaxSize()) {
                // Total number of prices
                val totalRecords = prices.prices.size

                // Maximum distance between dots (prices)
                val lineDistance = size.width / (totalRecords + 1)

                // Canvas height
                val cHeight = size.height

                // Add some kind of a "Padding" for the initial point where the line starts.
                var currentLineDistance = 0F //+ lineDistance

                prices.prices.forEachIndexed { index, priceRate ->
                    if (totalRecords >= index + 2) {
                        drawLine(
                            start = Offset(
                                x = currentLineDistance,
                                y = calculateYCoordinate(
                                    higherPriceRateValue = prices.maxPrice,
                                    currentPriceRate = priceRate.priceValue,
                                    canvasHeight = cHeight
                                )
                            ),
                            end = Offset(
                                x = currentLineDistance + lineDistance,
                                y =  calculateYCoordinate(
                                    higherPriceRateValue = prices.maxPrice,
                                    currentPriceRate = prices.prices[index + 1].priceValue,
                                    canvasHeight = cHeight
                                )
                            ),
                            color = Color.Blue,
                            strokeWidth = Stroke.DefaultMiter
                        )
                    }
                    currentLineDistance += lineDistance
                }

            }
        }
    }
}


private fun calculateYCoordinate(
    higherPriceRateValue: Double,
    currentPriceRate: Double,
    canvasHeight: Float
): Float {

    //TODO: remove multiplying by 100 and find a solution for small differences
    val maxAndCurrentValueDifference = (higherPriceRateValue - currentPriceRate)*100

    val relativePercentageOfScreen = (canvasHeight / higherPriceRateValue)

    return (maxAndCurrentValueDifference * relativePercentageOfScreen).toFloat()
}


@Composable
@Preview
fun prev() {

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
        prices = listOf(p1, p2, p3, p4, p5)
    )
    
    LineChart(prices = prices)
}