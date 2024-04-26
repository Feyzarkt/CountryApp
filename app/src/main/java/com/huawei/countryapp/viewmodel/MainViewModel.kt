package com.huawei.countryapp.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huawei.countryapp.model.Country

class MainViewModel : ViewModel() {

    val countryData = MutableLiveData<Country>()
    val countryLoad = MutableLiveData<Boolean>()
    val countryError = MutableLiveData<Boolean>()

    init {
        val timer = object : CountDownTimer (5000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished.toInt() == 4000){
                    val a = 5000
                }
            }
            override fun onFinish() {
                countryLoad.value = false
                val country = Country("Türkiye","turkeyImageUrl")
                countryData.value = country
            }
        }
        getDataFromService(timer)
    }

    private fun getDataFromService(timer: CountDownTimer){
        countryLoad.value = true
        timer.start()
    }

}