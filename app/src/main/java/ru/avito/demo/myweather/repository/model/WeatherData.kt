package ru.avito.demo.myweather.repository.model

import ru.avito.demo.myweather.api.model.CurrentWeather
import ru.avito.demo.myweather.api.model.ForecastWeatherItem

class WeatherData(
    val current: CurrentWeather,
    val forecast: List<ForecastWeatherItem>
)