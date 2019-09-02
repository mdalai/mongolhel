package com.example.android.learnmongolian.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//private const val BASE_URL = "http://192.168.1.15:8888/mongolapp/"
private const val BASE_URL = "http://39.104.16.60:8080/mongolapp/"

// create Moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// create Retrofit object
private val retrofit = Retrofit.Builder()
    //.addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface WebApiService {
    @GET("groups")
    fun getProperties():
        //Call<List<MarsProperty>>
            Deferred<List<GroupProperty>>
}



interface WebApiServiceWord {
    @GET("group/{gid}")
    fun getProperties(@Path("gid") int: Int ):
            Deferred<List<WordProperty>>
}

// public object to expose the Retrofit service
object WebApi {
    val retrofitService : WebApiService by lazy {
        retrofit.create(WebApiService::class.java)
    }

    val retrofitServiceWord : WebApiServiceWord by lazy {
        retrofit.create(WebApiServiceWord::class.java)
    }
}