package com.bondidos.currency_rates.data.remote

import com.bondidos.currency_rates.domain.model.CurrencyRate
import kotlinx.coroutines.flow.Flow

interface RemoteSource {
    fun fetchRates(): Flow<List<CurrencyRate>>
}