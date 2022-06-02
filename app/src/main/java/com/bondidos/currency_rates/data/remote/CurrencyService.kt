package com.bondidos.currency_rates.data.remote

import com.bondidos.currency_rates.data.model.CurrencyRateApi
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CurrencyService {
    @GET("/api/exrates/rates")
    suspend fun fetchRates(@QueryMap query: Map<String, String>): List<CurrencyRateApi>
}