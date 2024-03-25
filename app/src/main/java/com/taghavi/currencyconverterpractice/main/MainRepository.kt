package com.taghavi.currencyconverterpractice.main

import com.taghavi.currencyconverterpractice.data.model.CurrencyResponse
import com.taghavi.currencyconverterpractice.util.Resource

interface MainRepository {

    suspend fun getRates(base: String): Resource<CurrencyResponse>
}