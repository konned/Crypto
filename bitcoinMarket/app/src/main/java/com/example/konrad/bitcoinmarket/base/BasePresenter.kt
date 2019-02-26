package com.example.konrad.bitcoinmarket.base

import com.example.konrad.bitcoinmarket.injection.module.ContextModule
import com.example.konrad.bitcoinmarket.injection.module.NetworkModule
import com.example.konrad.bitcoinmarket.injection.module.component.DaggerPresenterInjector
import com.example.konrad.bitcoinmarket.injection.module.component.PresenterInjector
import com.example.konrad.bitcoinmarket.ui.market.MarketPresenter

abstract class BasePresenter<out V: BaseView>(protected val view: V) {

    /**
     * The inhector used to inject required dependencies
     */
    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .build()
    init{
        inject()
    }

    /**
     * function runs when presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * function runs when presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * function injects dependencies
     */
    private fun inject(){
        when (this) {
            is MarketPresenter -> injector.inject(this)
        }

    }
}