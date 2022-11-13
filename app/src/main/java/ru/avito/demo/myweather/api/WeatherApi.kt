package ru.avito.demo.myweather.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.avito.demo.myweather.api.model.CurrentWeather
import ru.avito.demo.myweather.api.model.ForecastWeatherResponse
import ru.avito.demo.myweather.api.model.GeocodingItem

private const val API_KEY = "21bd2dec2e4810155ff7d708b21313d2"

interface WeatherApi {

    @GET("/data/2.5/weather?units=metric&appid=$API_KEY")
    suspend fun getCurrentWeather(@Query("lat") lat: Float, @Query("lon") lon: Float): CurrentWeather

    @GET("/data/2.5/forecast?units=metric&appid=$API_KEY")
    suspend fun getForecast(@Query("lat") lat: Float, @Query("lon") lon: Float): ForecastWeatherResponse

    @GET("/geo/1.0/direct?limit=10&appid=$API_KEY")
    suspend fun geocoding(@Query("q") query: String): List<GeocodingItem>
}