package com.example.android.learnmongolian.learn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class WordCollectionViewModelFactory(
    private val groupId: Int ): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordCollectionViewModel::class.java)){
            return WordCollectionViewModel(groupId) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}