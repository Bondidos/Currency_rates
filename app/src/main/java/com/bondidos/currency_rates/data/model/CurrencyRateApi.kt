package com.bondidos.currency_rates.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyRateApi(
    @Json(name = "Cur_ID")
    val id: Int,

    @Json(name = "Date")
    val date: String,

    @Json(name = "Cur_Abbreviation")
    val abbreviation: String,

    @Json(name = "Cur_Scale")
    val scale: Int,

    @Json(name = "Cur_Name")
    val name: String,

    @Json(name = "Cur_OfficialRate")
    val officialRate: Double?,
)