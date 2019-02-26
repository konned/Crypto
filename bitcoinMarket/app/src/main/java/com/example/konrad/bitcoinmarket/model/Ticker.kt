package com.example.konrad.bitcoinmarket.model

import com.squareup.moshi.Json

data class Ticker(
        @Json(name = "base")
        val base: String,
        @Json(name = "change")
        val change: String,
        @Json(name = "markets")
        val markets: List<Market>,
        @Json(name = "price")
        val price: String,
        @Json(name = "target")
        val target: String,
        @Json(name = "volume")
        val volume: String
)