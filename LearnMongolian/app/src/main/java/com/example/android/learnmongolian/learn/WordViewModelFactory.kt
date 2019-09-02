package com.example.android.learnmongolian.learn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.learnmongolian.network.WordProperty

class WordViewModelFactory(private val wordProperty: WordProperty): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)){
            return WordViewModel(wordProperty) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}