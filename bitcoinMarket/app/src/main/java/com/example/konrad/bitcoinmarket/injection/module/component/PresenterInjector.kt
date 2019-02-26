package com.example.konrad.bitcoinmarket.injection.module.component

import com.example.konrad.bitcoinmarket.base.BaseView
import com.example.konrad.bitcoinmarket.injection.module.ContextModule
import com.example.konrad.bitcoinmarket.injection.module.NetworkModule
import com.example.konrad.bitcoinmarket.ui.market.MarketPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {

    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param marketPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(marketPresenter: MarketPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}