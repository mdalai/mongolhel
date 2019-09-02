package com.example.android.learnmongolian.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.android.learnmongolian.network.GroupProperty
import com.example.android.learnmongolian.network.WebApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.collections.ArrayList as ArrayList1
import androidx.databinding.ObservableBoolean



enum class WebApiStatus { LOADING, ERROR, DONE}

class HomeViewModel : ViewModel() {

    private val _status = MutableLiveData<WebApiStatus>()
    val status: LiveData<WebApiStatus> get() = _status

    //private val _property = MutableLiveData<GroupProperty>()
    //val property: LiveData<GroupProperty> get() = _property

    private val _properties = MutableLiveData<List<GroupProperty>>()
    val properties: LiveData<List<GroupProperty>>  get() = _properties

    // create a coroutine Job and CoroutineScope using main dispatcher
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // add navigation for detail - Property
    //private val _navigateToSelectedProperty = MutableLiveData<GroupProperty>()
    //val navigateToSelectedProperty: LiveData<GroupProperty> get() = _navigateToSelectedProperty

    // add navigation for detail - GroupId
    private val _navigateToSelectedProperty = MutableLiveData<Int>()
    val navigateToSelectedProperty: LiveData<Int> get() = _navigateToSelectedProperty

    // this var is for app:refreshing property in home_fragment.xml
    var isLoading = ObservableBoolean()

    init {
        getGroupProperties()
    }

    private fun getGroupProperties() {

        coroutineScope.launch {
            var getPropertiesDeferred = WebApi.retrofitService.getProperties()
            try {
                _status.value = WebApiStatus.LOADING

                val listResult = getPropertiesDeferred.await()
                //_response.value = "Success: ${listResult.size} Mars Properties retrived!"
                _status.value = WebApiStatus.DONE
                _properties.value = listResult
                isLoading.set(false)

            } catch(e: Exception) {
                //Log.d("HELLO","Failure: ${e.message}")
                _status.value = WebApiStatus.ERROR
                _properties.value = ArrayList1()
                isLoading.set(false)
            }
        }
        
    }

    // Should stop the Job when viewModel is gone
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(groupProperty: GroupProperty) {
        _navigateToSelectedProperty.value = groupProperty.group_id
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    // this method is for onRefreshListener property in home_fragment.xml
    fun onRefresh() {
        isLoading.set(true)
        getGroupProperties()
        //isLoading.set(false)
    }
}
