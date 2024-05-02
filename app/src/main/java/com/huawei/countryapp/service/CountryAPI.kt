package com.huawei.countryapp.service

import com.huawei.countryapp.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountryAPI {

//    https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Call<List<Country>>

}