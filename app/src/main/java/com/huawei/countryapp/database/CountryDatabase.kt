package com.huawei.countryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.huawei.countryapp.model.Country

@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile //sakladığı değerin Thread'ler tarafından okunmaya çalışıldığında hepsinde aynı değerin okunacağının garantisini verir.
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryDatabase::class.java,
                    "country-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}