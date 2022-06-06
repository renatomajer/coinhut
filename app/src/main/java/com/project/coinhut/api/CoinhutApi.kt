package com.project.coinhut.api

import com.project.coinhut.utils.Prices
import com.project.coinhut.utils.Token


interface CoinhutApi {

    suspend fun getToken(tokenId: String): Token

    suspend fun getPrices(tokenId: String): Prices

    suspend fun getTokens(): List<Token>
}