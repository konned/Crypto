package com.example.konrad.bitcoinmarket.ui.market

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.konrad.bitcoinmarket.R
import com.example.konrad.bitcoinmarket.databinding.ItemMarketBinding
import com.example.konrad.bitcoinmarket.model.Market
import com.example.konrad.bitcoinmarket.utils.SORT_NAME
import com.example.konrad.bitcoinmarket.utils.SORT_PRICE
import com.example.konrad.bitcoinmarket.utils.SORT_VOLUME

class MarketAdapter(private val context: Context) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    private var markets : List<Market> = listOf()
    private var sortComparator : String = SORT_NAME
    private var moneyForBitcoin : Double = 0.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketAdapter.MarketViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemMarketBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_market, parent, false)

        return MarketViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return markets.size
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bind(markets[position])
    }

    /**
    * Updates the list of markets of the adapter
    * @param markets the new list of posts of the adapter
    */
    fun updateMarkets(markets: List<Market>) {
        if (this.markets.isNotEmpty())
            updateIndicators(markets)
        this.markets = sortDataFromServer(markets)
        notifyDataSetChanged()
    }

    fun showBitcoinsToBuy(moneyToSpend: Double){
        moneyForBitcoin = moneyToSpend
        this.markets.forEach {
            if (moneyForBitcoin != 0.0)
            it.quantity = (moneyForBitcoin/it.price.toDouble()).toInt()
        }
    }

    /**
     * function which is checking if price is going down or up. Sets indicator.
     */
    fun updateIndicators(markets: List<Market>){
        markets.forEachIndexed { index, it ->
            if ((it.price.toDouble()) > this.markets[index].price.toDouble())
                it.priceIndicator = R.drawable.ic_arrow_upward_green_24dp
            else if ((it.price.toDouble()) < this.markets[index].price.toDouble())
                it.priceIndicator = R.drawable.ic_arrow_downward_red_24dp
            else
                it.priceIndicator = R.drawable.ic_stable_grey_24dp
            if (moneyForBitcoin != 0.0)
                it.quantity = (moneyForBitcoin/it.price.toDouble()).toInt()
        }
    }

    fun getDrawableForIndicator(indicatorType: String): Drawable{
        var drawable: Drawable
        if (indicatorType.equals("UP"))
            drawable = context.getDrawable(R.drawable.ic_arrow_upward_green_24dp)
        else if (indicatorType.equals("DOWN"))
            drawable = context.getDrawable(R.drawable.ic_arrow_downward_red_24dp)
        else
            drawable = context.getDrawable(R.drawable.ic_stable_grey_24dp)

        return drawable
    }

    fun sort(comparator: String){
        sortComparator = comparator
        when (comparator){
            SORT_NAME -> this.markets = this.markets.sortedWith(compareBy({ it.market }))
            SORT_PRICE -> this.markets = this.markets.sortedWith(compareBy({ it.price })).reversed()
            SORT_VOLUME -> this.markets = this.markets.sortedWith(compareBy({ it.volume })).reversed()
        }
        notifyDataSetChanged()
    }

    fun sortDataFromServer(markets: List<Market>) : List<Market>{
        var sortedMarket : List<Market> = markets
        when (sortComparator){
            SORT_NAME -> sortedMarket = markets.sortedWith(compareBy({ it.market }))
            SORT_PRICE -> sortedMarket = markets.sortedWith(compareBy({ it.price })).reversed()
            SORT_VOLUME -> sortedMarket = markets.sortedWith(compareBy({ it.volume })).reversed()
        }
        return sortedMarket
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Market item
     */
    class MarketViewHolder(private val binding: ItemMarketBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a market into the view
         */
        fun bind(market: Market) {
            binding.market = market
            binding.executePendingBindings()
        }
    }
}