package com.project.coinhut.viewmodel

import androidx.lifecycle.ViewModel
import com.project.coinhut.repository.CoinhutRepository
import com.project.coinhut.utils.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class EditHoldingsScreenViewModel(
    assetId: String
) : ViewModel(), KoinComponent {

    private val coinhutRepository: CoinhutRepository by inject()

    // returns Flow of Token with the value of current holdings
    // and all the other details
    fun getAsset(assetId: String): Flow<Token> {
        /* TODO: get holdings from database */
        // calls functions getToken and getHoldings
        return coinhutRepository.getAsset(assetId)
    }

    // sets the assets new holdings in the database
    // launches a coroutine and calls the repository's suspending function
    fun setHoldings(asset: Token, newValue: Double) {
        //TODO: add logic
        CoroutineScope(Dispatchers.Default).launch {
            coinhutRepository.setHoldings(asset, newValue)
        }
    }
}