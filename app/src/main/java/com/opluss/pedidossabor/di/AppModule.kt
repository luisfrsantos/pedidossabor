package com.opluss.pedidossabor.di

import com.opluss.pedidossabor.order.reposotory.OrderRepository
import com.opluss.pedidossabor.order.ui.AddOrderViewModel
import com.opluss.pedidossabor.order.ui.OrderAdapter
import com.opluss.pedidossabor.order.ui.OrderViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val app = module {
            single { OrderRepository() }
            viewModel { AddOrderViewModel(get()) }
            viewModel { OrderViewModel(get()) }
            factory { OrderAdapter(androidContext()) }
    }
}