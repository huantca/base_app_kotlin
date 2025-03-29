package com.example.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.database.model.MySample
import com.example.base.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<List<MySample>>(listOf())
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableStateFlow("hello world")
    val sharedFlow = _sharedFlow.asSharedFlow()


    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("shared flow")
        }
    }

    fun getAll() = viewModelScope.launch {

        myRepository.getAll().collect {
            _stateFlow.value = it
        }
    }

    fun insert(mySample: MySample) = viewModelScope.launch {
        myRepository.insert(mySample)
    }

}