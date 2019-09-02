package com.example.android.learnmongolian.learn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.android.learnmongolian.network.WordProperty

class WordViewModel(wordProperty: WordProperty) : ViewModel() {

    private val _word = MutableLiveData<WordProperty>()
    val word: LiveData<WordProperty> get() = _word

    init {
        _word.value = wordProperty
    }
}
