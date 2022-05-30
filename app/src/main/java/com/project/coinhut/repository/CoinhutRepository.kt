package com.project.coinhut.repository

import com.project.coinhut.utils.Prices
import com.project.coinhut.utils.Token
import kotlinx.coroutines.flow.Flow


interface CoinhutRepository {

    fun getToken(tokenId: String): Flow<Token>

    fun getPrices(tokenId: String): Flow<Prices>

    fun getAsset(assetId: String): Flow<Token>

    suspend fun setHoldings(asset: Token, newValue: Double)

    fun getTokens(): Flow<List<Token>>

    fun getNewAssets(): Flow<List<Token>>

    fun getAssets(): Flow<List<Token>>
}