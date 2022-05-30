package com.project.coinhut.viewmodel

import androidx.lifecycle.ViewModel
import com.project.coinhut.repository.CoinhutRepository
import com.project.coinhut.utils.Prices
import com.project.coinhut.utils.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.compose.inject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class DetailsScreenViewModel(
    tokenId: String
) : ViewModel(), KoinComponent {

    private val coinhutRepository: CoinhutRepository by inject()

    fun getToken(tokenId: String): Flow<Token> {
        return coinhutRepository.getToken(tokenId)
    }

    fun getPrices(tokenId: String): Flow<Prices> {
        return coinhutRepository.getPrices(tokenId)
    }


}