package com.project.coinhut.repository

import android.util.Log
import com.project.coinhut.api.CoinhutApi
import com.project.coinhut.api.CoinhutMockedApi
import com.project.coinhut.database.AppDatabase
import com.project.coinhut.database.AssetHoldingEntity
import com.project.coinhut.database.CoinhutMockedDatabase
import com.project.coinhut.utils.Prices
import com.project.coinhut.utils.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class CoinhutRepositoryImpl(
    private val coinhutApi: CoinhutApi,
    private val coinhutDatabase: CoinhutMockedDatabase,
    private val database: AppDatabase
) : CoinhutRepository {

    private val holdingsDao = database.holdingsDao()

    private object RefreshEvent

    override fun getToken(tokenId: String): Flow<Token> {
        return flow { emit(coinhutApi.getToken(tokenId)) }
    }

    override fun getPrices(tokenId: String): Flow<Prices> {
        return flow { emit(coinhutApi.getPrices(tokenId)) }
    }

    private val refreshAssetFlow = MutableSharedFlow<RefreshEvent>(replay = 1)

    private val assetFlow = refreshAssetFlow
        .onStart { refreshAssetFlow.emit(RefreshEvent) }

    // returns details about that token and its current holdings
    override fun getAsset(assetId: String): Flow<Token> {
        return assetFlow.map {
            //val holdings = coinhutDatabase.getHoldings(assetId)
            val assetHolding = holdingsDao.getAssetHolding(assetId).first()

            var holdings: Double

            if (assetHolding == null) {
                holdings = 0.0
            } else {
                holdings = assetHolding.holding
            }

            val token = coinhutApi.getToken(assetId)

            token.holding = holdings

            Log.d("debug_log", "TOKEN: $token")
            token

        }.shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )
    }

    override suspend fun setHoldings(asset: Token, newValue: Double) {
        //coinhutDatabase.setHoldings(asset, newValue)

        if(newValue > 0.0) {
            holdingsDao.insertAssetHolding(AssetHoldingEntity(id = asset.id, holding = newValue))
        } else {
            holdingsDao.deleteAssetHolding(assetHolding = AssetHoldingEntity(id = asset.id, holding = newValue))
        }

        refreshAssetFlow.emit(RefreshEvent)
    }

    override fun getTokens(): Flow<List<Token>> {
        return flow {
            emit(coinhutApi.getTokens())
        }
    }

    override fun getNewAssets(): Flow<List<Token>> {
        return flow {
            val tokens = coinhutApi.getTokens()
            //val assets = coinhutDatabase.getAllHoldings()
            val assets = holdingsDao.getAll().first().map {
                val token = coinhutApi.getToken(it.id)
                token.holding = it.holding
                token
            }
            var newAssets = mutableListOf<Token>()
            var inPossession: Boolean

            for (token in tokens) {
                inPossession = false
                for (asset in assets) {
                    if (token.id == asset.id) {
                        inPossession = true
                        break
                    }
                }

                if (!inPossession) {
                    // if the token is not already in possession, it can be added as new asset
                    newAssets.add(token)
                }
            }
            Log.d("debug_log", "NEW ASSETS: $newAssets")
            emit(newAssets)
        }
    }

    override fun getAssets(): Flow<List<Token>> {
//        return flow {
//            emit(coinhutDatabase.getAllHoldings())
//        }

        return holdingsDao.getAll().map {
            it.map {
                val token = coinhutApi.getToken(it.id)
                token.holding = it.holding
                token
            }
        }
    }

}