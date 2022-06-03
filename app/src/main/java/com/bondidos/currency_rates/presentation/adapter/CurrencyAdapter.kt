package com.bondidos.currency_rates.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.currency_rates.databinding.CurrencyItemBinding
import com.bondidos.currency_rates.domain.model.CurrencyRate

class CurrencyAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val currencyList = mutableListOf<CurrencyRate>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<CurrencyRate>) {
        currencyList.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CurrencyItemBinding.inflate(LayoutInflater.from(parent.context))
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    override fun getItemCount() = currencyList.size
}

class CurrencyViewHolder(private val binding: CurrencyItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: CurrencyRate) {
        with(binding) {
            currencyName.text = item.abbreviation
            currencyExchangeCount.text = "${item.scale} ${item.name}"
            actualRate.text = item.todayRate.toString()
            alternativeRate.text = item.alternativeRate.toString()
        }
    }
}
