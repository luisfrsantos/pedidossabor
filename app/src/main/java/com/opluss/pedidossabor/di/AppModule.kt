package com.opluss.pedidossabor.di

import com.opluss.pedidossabor.order.reposotory.OrderRepository
import com.opluss.pedidossabor.order.ui.AddOrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val app = module {
            single { OrderRepository() }
            viewModel { AddOrderViewModel(get()) }
    }
}