package com.bondidos.currency_rates.data.mapers

import com.bondidos.currency_rates.data.model.CurrencyRateApi
import com.bondidos.currency_rates.domain.model.CurrencyRate

fun createCurrencyRateList(
    todayRates: List<CurrencyRateApi>,
    alternativeRates: List<CurrencyRateApi>
): List<CurrencyRate> {
    val result: MutableList<CurrencyRate> = mutableListOf()
    for (i in todayRates.indices) {
        val currencyRateItem = createCurrencyRateItem(todayRates[i], alternativeRates[i])
        result.add(currencyRateItem)
    }
    return result
}

fun createCurrencyRateItem(today: CurrencyRateApi, alternativeRates: CurrencyRateApi) =
    CurrencyRate(
        id = today.id,
        abbreviation = today.abbreviation,
        scale = today.scale,
        name = today.name,
        todayRate = today.officialRate,
        alternativeRate = alternativeRates.officialRate,
        alternativeDate = alternativeRates.date,
        todayDate = today.date,
    )