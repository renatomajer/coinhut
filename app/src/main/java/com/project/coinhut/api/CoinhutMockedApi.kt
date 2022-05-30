package com.project.coinhut.api

import com.project.coinhut.utils.Prices
import com.project.coinhut.utils.Token


interface CoinhutMockedApi {

    fun getToken(tokenId: String): Token

    fun getPrices(tokenId: String): Prices

    fun getTokens(): List<Token>

}