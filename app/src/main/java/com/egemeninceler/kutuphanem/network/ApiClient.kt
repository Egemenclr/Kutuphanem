package com.egemeninceler.kutuphanem.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl("https://api.collectapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }
}