package ru.avito.demo.myweather.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import ru.avito.demo.myweather.api.WeatherApi
import ru.avito.demo.myweather.repository.model.WeatherData

interface WeatherRepository {
    suspend fun getWeather(lat: Float, lon: Float): WeatherData
}

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(lat: Float, lon: Float): WeatherData =
        withContext(Dispatchers.IO) {
            val weatherDeferred = async { weatherApi.getCurrentWeather(lat, lon) }
            val forecastDeferred = async { weatherApi.getForecast(lat, lon) }

            awaitAll(weatherDeferred, forecastDeferred)

            val currentWeather = weatherDeferred.await()
            val forecastWeather = forecastDeferred.await()

            return@withContext WeatherData(
                currentWeather,
                forecastWeather.list
            )
        }
}