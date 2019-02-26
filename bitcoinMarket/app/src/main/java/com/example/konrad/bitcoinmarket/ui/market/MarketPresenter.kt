package com.example.konrad.bitcoinmarket.ui.market

import android.os.Handler
import com.example.konrad.bitcoinmarket.R
import com.example.konrad.bitcoinmarket.base.BasePresenter
import com.example.konrad.bitcoinmarket.network.TickerApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject




class MarketPresenter(marketView: MarketView) : BasePresenter<MarketView>(marketView) {
    @Inject
    lateinit var tickerApi: TickerApi

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        loadMarkets()

        val ha = Handler()
        ha.postDelayed(object : Runnable {

            override fun run() {
                updateMarkets()

                ha.postDelayed(this, 10000)
            }
        }, 10000)
    }

    /**
     * Loads the markets from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadMarkets() {
        view.showLoading()
        subscription = tickerApi
                .getTicker()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { ticker -> view.updateMarkets(ticker.ticker.markets) },
                        { view.showError(R.string.unknown_error) }
                )
    }

    fun updateMarkets(){
        subscription = tickerApi
                .getTicker()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { ticker -> view.updateMarkets(ticker.ticker.markets) },
                        { view.showError(R.string.unknown_error) }
                )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}