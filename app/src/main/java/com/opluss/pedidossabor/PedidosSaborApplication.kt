package com.opluss.pedidossabor

import android.app.Application
import com.opluss.pedidossabor.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PedidosSaborApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PedidosSaborApplication)
            modules(AppModule.app)
        }
    }
}