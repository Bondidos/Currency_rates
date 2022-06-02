package com.bondidos.currency_rates.data.currency_service_impl
import com.bondidos.currency_rates.domain.utils.DateHelper

class ApiHelper {

    private val dateHelper = DateHelper()

    fun createYesterdayCurrencyQuery(): Map<String, String> = mapOf(
        QueryKeys.onDate to dateHelper.getYesterdayDate(),
        QueryKeys.periodicity to QueryParameters.everyDay.toString()
    )

    fun createTodayCurrencyQuery(): Map<String, String> = mapOf(
        QueryKeys.onDate to dateHelper.getTodayDate(),
        QueryKeys.periodicity to QueryParameters.everyDay.toString()
    )

    fun createTomorrowCurrencyQuery(): Map<String, String> = mapOf(
        QueryKeys.onDate to dateHelper.getTomorrowDate(),
        QueryKeys.periodicity to QueryParameters.everyDay.toString()
    )
}

object QueryKeys{
    const val onDate: String = "ondate"
    const val periodicity: String = "periodicity"
}

object QueryParameters{
    const val everyDay: Int = 0
}

