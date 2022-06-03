package com.bondidos.currency_rates.presentation.main_fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bondidos.currency_rates.R
import com.bondidos.currency_rates.databinding.FragmentMainBinding
import com.bondidos.currency_rates.di.appComponent
import com.bondidos.currency_rates.presentation.State
import com.bondidos.currency_rates.presentation.adapter.CurrencyAdapter
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    private val currencyAdapter: CurrencyAdapter by lazy { CurrencyAdapter() }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.injectMain(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initStateListening()
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL))
            adapter = currencyAdapter
        }
    }

    private fun initStateListening() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect{ state ->
                when(state){
                    is State.Loading -> binding.progressBar.isVisible = true
                    is State.Success -> {
                        binding.progressBar.isVisible = false
                        currencyAdapter.setData(state.data)
                    }
                    is State.Error ->{
                        //todo make snackBar
                    }
                    else -> Unit
                }
            }
        }
    }
}
