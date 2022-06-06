package com.project.coinhut.api

import android.icu.util.Calendar
import android.util.Log
import com.project.coinhut.utils.Prices
import com.project.coinhut.utils.PricesResponse
import com.project.coinhut.utils.Token
import com.project.coinhut.utils.TokenResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.util.date.*
import java.io.Console
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class CoinhutApiImpl(
    private val client: HttpClient
) : CoinhutApi {
    override suspend fun getToken(tokenId: String): Token {
        return try {
            client.get<TokenResponse>(
                "https://api.coingecko.com/api/v3/coins/$tokenId?localization=false&tickers=false&market_data=true&community_data=true&developer_data=false&sparkline=false"
            )
                .toToken()
        } catch (exc: Exception) {
            Token()
        }
    }

    override suspend fun getPrices(tokenId: String): Prices {

        // UNIX timestamp
        val current: Long = System.currentTimeMillis() / 1000L

        // from current time subtract the value of seconds that represents one day (86400s)
        val dayBefore: Long = current - 86400L

        return try {
            var p =
                client.get<PricesResponse>("https://api.coingecko.com/api/v3/coins/$tokenId/market_chart/range?vs_currency=eur&from=$dayBefore&to=$current")
            Log.d("debug_log", "${p.toPrices()}")
            p.toPrices()

        } catch (exc: Exception) {
            Log.d("debug_log", "${exc.toString()}")
            Prices(0.0, 0.0, emptyList())
        }
    }

    override suspend fun getTokens(): List<Token> {
        return try {
            client.get<List<Token>>("https://api.coingecko.com/api/v3/coins/markets?vs_currency=eur&order=market_cap_desc&per_page=20&page=1&sparkline=false&price_change_percentage=24h")
        } catch (exc: Exception) {
            emptyList()
        }
    }

}