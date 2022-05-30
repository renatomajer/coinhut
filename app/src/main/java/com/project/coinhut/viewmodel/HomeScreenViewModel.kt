package com.project.coinhut.viewmodel

import androidx.lifecycle.ViewModel
import com.project.coinhut.repository.CoinhutRepository
import com.project.coinhut.utils.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class HomeScreenViewModel() : ViewModel(), KoinComponent {

    private val coinhutRepository: CoinhutRepository by inject()

    fun getTokens(): Flow<List<Token>> {
        return coinhutRepository.getTokens()
    }
}