package com.project.coinhut.database

import android.util.Pair
import com.project.coinhut.utils.Token
import java.util.function.Predicate


class CoinhutMockedDatabaseImpl() : CoinhutMockedDatabase {
    private var holdings: MutableList<Token> = mutableListOf() // TODO: replace with immutable

    override suspend fun setHoldings(token: Token, newValue: Double) {
        var isPresent = false
        var remove = false
        var index = -1

        for (asset in holdings) {
            if (asset.id == token.id) {
                isPresent = true

                if (newValue > 0.0) {
                    asset.holding = newValue
                } else {
                    remove = true
                    index = holdings.indexOf(asset)
                }

                break
            }
        }

        if(remove) {
            holdings.removeAt(index)
        }

        if (!isPresent && newValue > 0.0) {
            token.holding = newValue
            holdings.add(token)
        }
    }

    override suspend fun getHoldings(tokenId: String): Double {
        var hold = 0.0
        for (asset in holdings) {
            if (asset.id == tokenId) {
                hold = asset.holding
                break
            }
        }
        return hold
    }

    override suspend fun getAllHoldings(): List<Token> {
        return holdings.reversed()
    }

}