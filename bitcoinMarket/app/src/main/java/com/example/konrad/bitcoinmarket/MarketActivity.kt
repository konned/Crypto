package com.example.konrad.bitcoinmarket

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.konrad.bitcoinmarket.base.BaseActivity
import com.example.konrad.bitcoinmarket.databinding.ActivityMarketBinding
import com.example.konrad.bitcoinmarket.model.Market
import com.example.konrad.bitcoinmarket.ui.market.MarketAdapter
import com.example.konrad.bitcoinmarket.ui.market.MarketPresenter
import com.example.konrad.bitcoinmarket.ui.market.MarketView
import com.example.konrad.bitcoinmarket.utils.SORT_NAME
import com.example.konrad.bitcoinmarket.utils.SORT_PRICE
import com.example.konrad.bitcoinmarket.utils.SORT_VOLUME
import kotlinx.android.synthetic.main.activity_market.*

class MarketActivity: BaseActivity<MarketPresenter>(), MarketView {

    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityMarketBinding

    /**
     * The adapter for the list of posts
     */
    private val marketsAdapter = MarketAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_market)
        binding.adapter = marketsAdapter
        binding.markets.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        presenter.onViewCreated()

        inputMoney.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (inputMoney.text.isNotEmpty())
                    marketsAdapter.showBitcoinsToBuy(inputMoney.text.toString().toDouble())
                else
                    marketsAdapter.showBitcoinsToBuy("0.0".toDouble())
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.market_sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.sort_by_name -> {
            marketsAdapter.sort(SORT_NAME)
            true
        }
        R.id.sort_by_price -> {
            marketsAdapter.sort(SORT_PRICE)
            true
        }
        R.id.sort_by_volume -> {
            marketsAdapter.sort(SORT_VOLUME)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun updateMarkets(markets: List<Market>) {
        marketsAdapter.updateMarkets(markets)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): MarketPresenter {
        return MarketPresenter(this)
    }
}
