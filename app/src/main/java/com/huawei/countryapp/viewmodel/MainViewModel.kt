package com.huawei.countryapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huawei.countryapp.model.Country
import com.huawei.countryapp.service.CountryAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val countryAPI = CountryAPIService()

    val countryData = MutableLiveData<List<Country>>()
    val countryLoad = MutableLiveData<Boolean>()
    val countryError = MutableLiveData<Boolean>()

    fun getDataFromAPI(){
        countryLoad.value = true

        countryAPI.getData().enqueue(object: Callback<List<Country>>{
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                countryData.value = response.body()
                countryLoad.value = false
                countryError.value = false
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                countryLoad.value = false
                countryError.value = true
                Log.e("RetrofitError",t.message.toString())
            }
        })

    }

}