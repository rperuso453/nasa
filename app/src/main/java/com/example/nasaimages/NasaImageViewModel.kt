package com.example.nasaimages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaimages.model.NasaImages
import com.example.nasaimages.network.NasaClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NasaImageViewModel : ViewModel() {
    var nasaImageResponse = MutableLiveData<NasaImages>()

    fun getNasaData() {
        val response = NasaClientInstance.getNasaServices()
            .getNasaImages("DEMO_KEY")

        response.enqueue(object : Callback<NasaImages> {
            override fun onFailure(call: Call<NasaImages>, t: Throwable) {
                // Handle error case
            }

            override fun onResponse(
                call: Call<NasaImages>,
                response: Response<NasaImages>
            ) {
                nasaImageResponse.value = response.body()
            }

        })
    }
}