package ru.avito.demo.myweather.api.model

data class GeocodingItem(
    val name: String,
    val state: String,
    val lat: Float,
    val lon: Float
)