package com.example.konrad.bitcoinmarket.model

import com.squareup.moshi.Json

data class TickerResponse(
        @Json(name = "error")
        val error: String,
        @Json(name = "success")
        val success: Boolean,
        @Json(name = "ticker")
        val ticker: Ticker,
        @Json(name = "timestamp")
        val timestamp: Int
)