package com.taghavi.currencyconverterpractice

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.taghavi.currencyconverterpractice.databinding.ActivityMainBinding
import com.taghavi.currencyconverterpractice.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString(),
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when (event) {
                    is MainViewModel.CurrencyEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.BLACK)
                        binding.tvResult.text = event.resultText
                    }

                    is MainViewModel.CurrencyEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.RED)
                        binding.tvResult.text = event.errorText
                    }

                    is MainViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    else -> Unit
                }
            }
        }
    }
}