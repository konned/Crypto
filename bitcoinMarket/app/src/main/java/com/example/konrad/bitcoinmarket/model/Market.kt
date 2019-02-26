package com.example.konrad.bitcoinmarket.model

import com.squareup.moshi.Json

data class Market(
        @Json(name = "market")
        val market: String,
        @Json(name = "price")
        val price: String,
        @Json(name = "volume")
        val volume: Double,
        var priceIndicator: Int,
        var quantity: Int
)