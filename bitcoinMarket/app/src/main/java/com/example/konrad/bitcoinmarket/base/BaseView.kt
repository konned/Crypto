package com.example.konrad.bitcoinmarket.base

import android.content.Context

interface BaseView {

    /**
     * returns conext in which application is running
     */
    fun getContext(): Context
}