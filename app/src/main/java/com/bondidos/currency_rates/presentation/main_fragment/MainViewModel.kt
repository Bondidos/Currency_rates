package com.bondidos.currency_rates.presentation.main_fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bondidos.currency_rates.domain.usecases.FetchCurrencyRatesUseCase
import com.bondidos.currency_rates.presentation.State
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val fetchCurrencyRatesUseCase: FetchCurrencyRatesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(State.Initial)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<String>()
    val uiEvent: SharedFlow<String> = _uiEvent.asSharedFlow()

    init {
        fetchRates()
    }

    private fun fetchRates(){
        viewModelScope.launch {
            fetchCurrencyRatesUseCase()
                .onStart { _uiState.emit(State.Loading) }
                .catch {
                    _uiState.emit(State.Error)
                    Log.d("VM", this.toString())
                }
                .collect {
                    _uiState.emit(State.Success(it))
                    Log.d("VM", it.toString())
                }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(
    private val fetchCurrencyRatesUseCase: FetchCurrencyRatesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(fetchCurrencyRatesUseCase) as T
    }
}