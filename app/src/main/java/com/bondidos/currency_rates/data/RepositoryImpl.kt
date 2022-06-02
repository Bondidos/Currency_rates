package com.bondidos.currency_rates.data
import com.bondidos.currency_rates.data.remote.RemoteSource
import com.bondidos.currency_rates.domain.Repository
import com.bondidos.currency_rates.domain.model.CurrencyRate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource
) : Repository {
    override fun fetchCurrencyRates(): Flow<List<CurrencyRate>> = remoteSource.fetchRates()
}
