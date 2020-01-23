package com.example.nasaimages.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object NasaClientInstance {

    fun getNasaServices(): GetDataService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nasa.gov/")
            .build()
        return retrofit.create(GetDataService::class.java)
    }
}

