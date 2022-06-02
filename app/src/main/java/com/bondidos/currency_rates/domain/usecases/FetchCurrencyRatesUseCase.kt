package com.bondidos.currency_rates.domain.usecases

import com.bondidos.currency_rates.domain.Repository
import com.bondidos.currency_rates.domain.model.CurrencyRate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCurrencyRatesUseCase @Inject constructor(
    private val repository: Repository
    ){
    operator fun invoke(): Flow<List<CurrencyRate>> = repository.fetchCurrencyRates()
}