package com.huawei.countryapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val capital: String,
    @ColumnInfo(name = "region")
    @SerializedName("region")
    val region: String,
    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val currency: String,
    @ColumnInfo(name = "language")
    @SerializedName("language")
    val language: String,
    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val flagUrl : String
) : Parcelable

//"name": "Algeria",
//"capital": "Algiers",
//"region": "Africa",
//"currency": "DZD",
//"flag": "https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/dza.png",
//"language": "Arabic"