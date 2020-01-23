package com.example.nasaimages.network

import com.example.nasaimages.model.NasaImages
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("planetary/apod")
    fun getNasaImages(@Query("api_key") apiKey: String): Call<NasaImages>
}