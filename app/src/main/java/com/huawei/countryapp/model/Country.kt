package com.huawei.countryapp.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val name: String,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("flag")
    val flagUrl : String
)

//"name": "Algeria",
//"capital": "Algiers",
//"region": "Africa",
//"currency": "DZD",
//"flag": "https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/dza.png",
//"language": "Arabic"