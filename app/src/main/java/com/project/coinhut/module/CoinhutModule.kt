package com.project.coinhut.module

import com.project.coinhut.api.CoinhutMockedApi
import com.project.coinhut.api.CoinhutMockedApiImpl
import com.project.coinhut.database.CoinhutMockedDatabase
import com.project.coinhut.database.CoinhutMockedDatabaseImpl
import com.project.coinhut.repository.CoinhutRepository
import com.project.coinhut.repository.CoinhutRepositoryImpl
import com.project.coinhut.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val coinhutModule = module {

    single<CoinhutMockedApi>{ CoinhutMockedApiImpl() }

    single<CoinhutMockedDatabase>{ CoinhutMockedDatabaseImpl() }

    single<CoinhutRepository>{ CoinhutRepositoryImpl(coinhutApi = get(), coinhutDatabase = get()) }

    viewModel { params ->
        DetailsScreenViewModel(params.get())
    }

    viewModel { params ->
        EditHoldingsScreenViewModel(params.get())
    }

    viewModel {
        HomeScreenViewModel()
    }

    viewModel {
        NewAssetScreenViewModel()
    }

    viewModel {
        PortfolioScreenViewModel()
    }
}