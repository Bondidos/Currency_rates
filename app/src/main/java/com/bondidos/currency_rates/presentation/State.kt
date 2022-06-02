package com.bondidos.currency_rates.presentation

import com.bondidos.currency_rates.domain.model.CurrencyRate

sealed class State {
    object Initial: State()
    object Loading: State()
    class Success(val data: List<CurrencyRate>): State()
    object Error: State()
}