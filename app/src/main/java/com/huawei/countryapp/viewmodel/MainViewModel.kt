package com.huawei.countryapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.countryapp.database.CountryDao
import com.huawei.countryapp.database.CountryDatabase
import com.huawei.countryapp.model.Country
import com.huawei.countryapp.service.CountryAPIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val countryAPI = CountryAPIService()

    val countryData = MutableLiveData<List<Country>>()
    val countryLoad = MutableLiveData<Boolean>()
    val countryError = MutableLiveData<Boolean>()

    private var countryDatabase: CountryDatabase? = null
    private var countryDao: CountryDao? = null
    val country = MutableLiveData<Country>()
    init {
        countryDatabase = CountryDatabase.getInstance(application)
        countryDao = countryDatabase?.countryDao()
    }

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

    fun insertAll(list: List<Country>) = viewModelScope.launch {
        countryDao?.insertAll(list)
    }

    fun findByName (name:String) = viewModelScope.launch {
        country.value = countryDao?.findByName(name)
    }
}