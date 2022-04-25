package com.trusov.factorialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import com.trusov.factorialtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.buttonCalculate.setOnClickListener {
            viewModel.calculate(binding.editTextNumber.text.toString())
        }
    }

    private fun observeViewModel() {
        with(binding) {
            viewModel.state.observe(this@MainActivity) {
                when (it) {
                    is Error -> Toast.makeText(
                        this@MainActivity,
                        "You did not entered value",
                        Toast.LENGTH_SHORT
                    ).show()
                    is Progress -> {
                        progressBarLoading.isGone = false
                        buttonCalculate.isEnabled = true
                    }
                    is Factorial -> textViewFactorial.text = it.value
                }



            }
        }
    }
}