package com.lucascabral.x_menapp.ui.di

import com.lucascabral.x_menapp.data.api.XMenService
import com.lucascabral.x_menapp.data.network.remote.XMenRetrofit
import com.lucascabral.x_menapp.data.repository.CharactersRepository
import com.lucascabral.x_menapp.ui.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val xMenModule = module {

    single { XMenRetrofit.createService(XMenService::class.java) }

    single { CharactersRepository(get()) }

    viewModel { CharactersViewModel(get()) }
}