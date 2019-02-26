package com.example.konrad.bitcoinmarket.ui.market

import android.support.annotation.StringRes
import com.example.konrad.bitcoinmarket.base.BaseView
import com.example.konrad.bitcoinmarket.model.Market

interface MarketView : BaseView {

    /**
     * Updates the previous markets by the specified ones
     * @param markets the list of posts that will replace existing ones
     */
    fun updateMarkets(markets: List<Market>)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

    /**
     * Displays an error in the view
     * @param errorResId the resource id of the error to display in the view
     */
    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()
}