package com.example.wordle.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordle.data.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WordViewModel: ViewModel() {
    private val _word = MutableStateFlow<String>("Loading...")
    val word: StateFlow<String> get() = _word

    init {
        fetchRandomWord()
    }

    fun fetchRandomWord() {
        viewModelScope.launch {
            try {
                val result = RetrofitClient.api.getRandomWord()
                _word.value = result.firstOrNull() ?: "No word found"
            } catch (e: Exception) {
                _word.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}