package com.project.coinhut.api

import com.project.coinhut.utils.*


class CoinhutMockedApiImpl() : CoinhutMockedApi {
    override fun getToken(tokenId: String): Token {
        return if(tokenId == "bitcoin") t1.copy()
        else if(tokenId == "ethereum") t2.copy()
        else if(tokenId == "tether") t3.copy()
        else t1.copy()
    }

    override fun getPrices(tokenId: String): Prices {
        return if(tokenId == "bitcoin") prices
        else prices
    }

    override fun getTokens(): List<Token> {
        return tokens
    }

}