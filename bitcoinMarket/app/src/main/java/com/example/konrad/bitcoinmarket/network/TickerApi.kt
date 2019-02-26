package com.example.konrad.bitcoinmarket.network

import com.example.konrad.bitcoinmarket.model.TickerResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface TickerApi {

    @GET("full/btc-usd")
    @Headers("Content-Type: application/json")
    fun getTicker(): Observable<TickerResponse>
}