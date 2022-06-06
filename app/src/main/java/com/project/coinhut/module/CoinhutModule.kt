package com.project.coinhut.module

import android.util.Log
import com.project.coinhut.api.CoinhutApi
import com.project.coinhut.api.CoinhutApiImpl
import com.project.coinhut.api.CoinhutMockedApi
import com.project.coinhut.api.CoinhutMockedApiImpl
import com.project.coinhut.database.AppDatabase
import com.project.coinhut.database.CoinhutMockedDatabase
import com.project.coinhut.database.CoinhutMockedDatabaseImpl
import com.project.coinhut.repository.CoinhutRepository
import com.project.coinhut.repository.CoinhutRepositoryImpl
import com.project.coinhut.viewmodel.*
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val coinhutModule = module {

    single<HttpClient> {
        HttpClient(Android) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("HTTP", message)
                    }
                }
                level = LogLevel.ALL
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single<AppDatabase>{ AppDatabase.getInstance(androidApplication()) }

    single<CoinhutApi>{ CoinhutApiImpl(client = get()) }

    single<CoinhutMockedApi> { CoinhutMockedApiImpl() }

    single<CoinhutMockedDatabase> { CoinhutMockedDatabaseImpl() }

    single<CoinhutRepository> { CoinhutRepositoryImpl(coinhutApi = get(), coinhutDatabase = get(), database = get()) }

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