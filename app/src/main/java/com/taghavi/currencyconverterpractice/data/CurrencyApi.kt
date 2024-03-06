package com.taghavi.currencyconverterpractice.data

import com.taghavi.currencyconverterpractice.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/latest")
    suspend fun getRates(
        @Query("apikey") apikey: String,
    ): Response<CurrencyResponse>
}