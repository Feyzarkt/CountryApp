package com.huawei.countryapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.huawei.countryapp.model.Country
@Dao
interface CountryDao {
    @Query("SELECT * FROM countries")
    suspend fun getAll(): List<Country>

    @Query("SELECT * FROM countries WHERE name = :countryName")
    suspend fun findByName(countryName: String): Country

    @Insert
    suspend fun insertAll(list: List<Country>)

}