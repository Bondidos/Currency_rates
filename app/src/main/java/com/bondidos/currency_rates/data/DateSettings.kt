package com.bondidos.currency_rates.data

import kotlinx.coroutines.flow.Flow

interface DateSettings {
    fun setTodayDate(date: String)
    fun setAlternativeDate(date: String)
    fun subscribeTodayDate(): Flow<String>
    fun subscribeAlternativeDate(): Flow<String>
}