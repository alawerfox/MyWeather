package ru.avito.demo.myweather.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.avito.demo.myweather.api.WeatherApi
import ru.avito.demo.myweather.api.model.GeocodingItem
import ru.avito.demo.myweather.repository.model.CurrentCity

interface CityRepository {
    suspend fun searchCity(search: String): List<GeocodingItem>
    suspend fun getCurrentCity(): CurrentCity
    suspend fun saveCurrentCity(currentCity: CurrentCity)
}

class CityRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val sharedPreferences: SharedPreferences
) : CityRepository {
    override suspend fun searchCity(search: String): List<GeocodingItem> {
        return weatherApi.geocoding(search)
    }

    override suspend fun getCurrentCity() =
        CurrentCity(
            sharedPreferences.getString(CITY_NAME, "") ?: "",
            sharedPreferences.getFloat(CITY_LAT, 0f),
            sharedPreferences.getFloat(CITY_LON, 0f),
        )

    override suspend fun saveCurrentCity(currentCity: CurrentCity) {
        sharedPreferences.edit {
            putString(CITY_NAME, currentCity.name)
            putFloat(CITY_LAT, currentCity.lat)
            putFloat(CITY_LON, currentCity.lon)
        }
    }

    private companion object {
        const val CITY_NAME = "name"
        const val CITY_LAT = "lat"
        const val CITY_LON = "lon"
    }
}