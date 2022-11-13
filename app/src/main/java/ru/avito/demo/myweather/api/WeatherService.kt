package ru.avito.demo.myweather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi by lazy { retrofit.create(WeatherApi::class.java) }
}