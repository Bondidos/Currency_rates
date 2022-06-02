package com.bondidos.currency_rates.domain.model

data class CurrencyRate(
    val id: Int,

    val abbreviation: String,

    val scale: Int,

    val name: String,

    val todayRate: Double?,

    val alternativeRate: Double?,

    val alternativeDate: String,

    val todayDate: String,
)