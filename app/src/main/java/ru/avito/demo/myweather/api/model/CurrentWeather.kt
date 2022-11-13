package ru.avito.demo.myweather.api.model

data class CurrentWeather(
    val weather: List<WeatherItem>,
    val main: WeatherMain
)

data class WeatherItem(
    val id: Int,
    val description: String,
    val icon: String
) {
    val imageUrl: String
        get() = "https://openweathermap.org/img/wn/${icon}@2x.png"
}