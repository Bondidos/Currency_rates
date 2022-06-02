package com.bondidos.currency_rates.domain

import com.bondidos.currency_rates.domain.model.CurrencyRate
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun fetchCurrencyRates(): Flow<List<CurrencyRate>>
}