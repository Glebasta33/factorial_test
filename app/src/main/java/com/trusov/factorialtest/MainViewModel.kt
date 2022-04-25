package com.trusov.factorialtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    var error: LiveData<Boolean> = _error

    private val _factorial = MutableLiveData<String>()
    var factorial: LiveData<String> = _factorial

    private val _progress = MutableLiveData<Boolean>()
    var progress: LiveData<Boolean> = _progress

    fun calculate(value: String?) {
        _progress.value = true
        if(value.isNullOrEmpty()){
            _progress.value = false
            _error.value = true
            return
        }
        viewModelScope.launch {
            val number = value.toLong()
            // calculate
            delay(2000)
            _progress.value = false
            _factorial.value = number.toString()
        }
    }

}