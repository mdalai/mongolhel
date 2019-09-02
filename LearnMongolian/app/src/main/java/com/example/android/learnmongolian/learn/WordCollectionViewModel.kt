package com.example.android.learnmongolian.learn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.android.learnmongolian.home.WebApiStatus
import com.example.android.learnmongolian.network.WebApi
import com.example.android.learnmongolian.network.WordProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WordCollectionViewModel(groupId: Int) : ViewModel() {
    //private val _selectedGroupId = MutableLiveData<Int>()
    //val selectedGroupId: LiveData<Int> get() = _selectedGroupId

    // create a coroutine Job and CoroutineScope using main dispatcher
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<WebApiStatus>()
    val status: LiveData<WebApiStatus> get() = _status

    //private val _property = MutableLiveData<GroupProperty>()
    //val property: LiveData<GroupProperty> get() = _property

    private val _properties = MutableLiveData<List<WordProperty>>()
    val properties: LiveData<List<WordProperty>>  get() = _properties

    init {
        getWordProperties(groupId)
    }

    fun getWordProperties(gId: Int) {

        coroutineScope.launch {
            var getPropertiesDeferred = WebApi.retrofitServiceWord.getProperties(gId)
            try {
                _status.value = WebApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()
                _status.value = WebApiStatus.DONE
                _properties.value = listResult

                Log.d("DEBUG", listResult.size.toString())

            } catch(e: Exception) {
                _status.value = WebApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }

    }

    // Should stop the Job when viewModel is gone
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
