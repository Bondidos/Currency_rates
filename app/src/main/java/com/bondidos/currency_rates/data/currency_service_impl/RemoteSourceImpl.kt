package com.bondidos.currency_rates.data.currency_service_impl

import com.bondidos.currency_rates.data.mapers.createCurrencyRateList
import com.bondidos.currency_rates.data.model.CurrencyRateApi
import com.bondidos.currency_rates.data.remote.CurrencyService
import com.bondidos.currency_rates.data.remote.RemoteSource
import com.bondidos.currency_rates.domain.model.CurrencyRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(private val currencyService: CurrencyService) :
    RemoteSource {

    private val apiHelper = ApiHelper()

    private suspend fun fetchTodayRates(): List<CurrencyRateApi> =
        currencyService.fetchRates(apiHelper.createTodayCurrencyQuery())

    private suspend fun fetchYesterdayRates(): List<CurrencyRateApi> =
        currencyService.fetchRates(apiHelper.createYesterdayCurrencyQuery())

    private suspend fun fetchTomorrowRates(): List<CurrencyRateApi> =
        currencyService.fetchRates(apiHelper.createTomorrowCurrencyQuery())

    override fun fetchRates(): Flow<List<CurrencyRate>> {
        return flow {
            val todayRates: List<CurrencyRateApi> = fetchTodayRates()
            val alternativeRates: List<CurrencyRateApi> = fetchTomorrowOrYesterdayRates()
            val currencyRates = createCurrencyRateList(todayRates,alternativeRates)
            emit(currencyRates)
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun fetchTomorrowOrYesterdayRates(): List<CurrencyRateApi> =
        fetchTomorrowRates().ifEmpty { fetchYesterdayRates() }
}