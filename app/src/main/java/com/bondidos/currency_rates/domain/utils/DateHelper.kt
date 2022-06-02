package com.bondidos.currency_rates.domain.utils

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private val gregorianCalendar = GregorianCalendar().apply { isLenient = false }
    private val format = SimpleDateFormat("yyyy.MM.dd", Locale("ru"))

    fun getTodayDate(): String {
        setActualDate()
        return format.format(gregorianCalendar.time)
    }

    fun getYesterdayDate(): String {
        setActualDate()
        setYesterdayDate()
        return format.format(gregorianCalendar.time)
    }

    fun getTomorrowDate(): String {
        setActualDate()
        setTomorrowDate()
        return format.format(gregorianCalendar.time)
    }

    private fun setYesterdayDate() = gregorianCalendar.add(Calendar.DATE, -1)
    private fun setTomorrowDate() = gregorianCalendar.add(Calendar.DATE, 1)

    private fun setActualDate() {
        gregorianCalendar.add(Calendar.DATE, 1)
        gregorianCalendar.apply {
            set(year, month, day)
        }
    }
}