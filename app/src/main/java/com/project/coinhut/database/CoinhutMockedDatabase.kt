package com.project.coinhut.database

import com.project.coinhut.utils.Token

interface CoinhutMockedDatabase {

    suspend fun setHoldings(token: Token, newValue: Double)

    suspend fun getHoldings(tokenId: String): Double

    suspend fun getAllHoldings(): List<Token>
}