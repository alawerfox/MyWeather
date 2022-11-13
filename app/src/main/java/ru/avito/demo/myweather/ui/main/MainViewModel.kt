package ru.avito.demo.myweather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.avito.demo.myweather.repository.CityRepository
import ru.avito.demo.myweather.repository.WeatherRepository
import ru.avito.demo.myweather.repository.model.WeatherData

class MainViewModel(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weather = MutableLiveData<WeatherData>()
    val weather: LiveData<WeatherData> = _weather

    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    fun loadData() {
        viewModelScope.launch {
            val currentCity = cityRepository.getCurrentCity()
            if (currentCity.name.isBlank()) return@launch

            _city.value = currentCity.name
            _weather.value = weatherRepository.getWeather(currentCity.lat, currentCity.lon)
        }
    }
}