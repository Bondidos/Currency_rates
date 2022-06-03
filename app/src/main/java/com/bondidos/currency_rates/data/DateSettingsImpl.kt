package com.bondidos.currency_rates.data

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DateSettingsImpl constructor(
    private val sharedPreferences: SharedPreferences
) : DateSettings {

    override fun setTodayDate(date: String) {
        sharedPreferences.edit()
            .putString(TODAY_DATE, date)
            .apply()
    }

    override fun setAlternativeDate(date: String) {
        sharedPreferences.edit()
            .putString(ALTERNATIVE_DATE, date)
            .apply()
    }

    override fun subscribeTodayDate(): Flow<String> {
       return flow<String> {

       }.flowOn(Dispatchers.IO)
    }

    override fun subscribeAlternativeDate(): Flow<String> {
        TODO("Not yet implemented")
    }

    companion object {
        const val TODAY_DATE = "todayDate"
        const val ALTERNATIVE_DATE = "alternativeDate"
    }
}
