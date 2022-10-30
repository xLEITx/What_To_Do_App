package com.leit.whattodoapp.model

import com.leit.whattodoapp.network.Activity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://www.boredapi.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ActivityApiService {
    @GET("activity")
    suspend fun getActivity(): Activity
}

object ActivityApi {
    val retrofitService: ActivityApiService by lazy {
        retrofit.create(ActivityApiService::class.java)
    }
}