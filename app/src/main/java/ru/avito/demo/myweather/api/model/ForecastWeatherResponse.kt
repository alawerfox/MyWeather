package ru.avito.demo.myweather.api.model

import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset


data class ForecastWeatherResponse(
    val list: List<ForecastWeatherItem>
)

data class ForecastWeatherItem(
    val dt: Long,
    val main: WeatherMain,
    val weather: List<ForecastWeather>
) {
    val date: LocalDateTime
        get() =  LocalDateTime.ofInstant(Instant.ofEpochSecond(dt), ZoneOffset.UTC)
}

data class ForecastWeather(
    val id: Int,
    val description: String,
    val icon: String
) {
    val imageUrl: String
        get() = "https://openweathermap.org/img/wn/${icon}.png"
}