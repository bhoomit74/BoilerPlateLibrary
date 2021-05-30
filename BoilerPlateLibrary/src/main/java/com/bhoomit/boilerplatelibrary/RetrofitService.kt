package com.bhoomit.boilerplatelibrary

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


fun getRetrofit(baseUrl: String): Retrofit {
    val client = OkHttpClient.Builder()

    client.addInterceptor { chain: Interceptor.Chain ->
        val request =
            chain.request().newBuilder().addHeader("Content-Type", "application/json").build()
        chain.proceed(request)
    }

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(client.build()).build()

}