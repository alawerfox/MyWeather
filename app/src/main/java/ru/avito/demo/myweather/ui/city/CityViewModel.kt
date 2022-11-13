package ru.avito.demo.myweather.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.avito.demo.myweather.api.model.GeocodingItem
import ru.avito.demo.myweather.repository.CityRepository
import ru.avito.demo.myweather.repository.model.CurrentCity

class CityViewModel(
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _city = MutableLiveData<List<GeocodingItem>>()
    val city: LiveData<List<GeocodingItem>> = _city

    private val _back = MutableLiveData<Unit>()
    val back: LiveData<Unit> = _back

    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            _city.value = cityRepository.searchCity(query)
        }
    }

    fun saveCurrentCity(geocodingItem: GeocodingItem) {
        viewModelScope.launch {
            val currentCity = CurrentCity(geocodingItem.name, geocodingItem.lat, geocodingItem.lon)
            cityRepository.saveCurrentCity(currentCity)
            _back.value = Unit
        }
    }
}