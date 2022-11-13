package ru.avito.demo.myweather.di

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.avito.demo.myweather.api.WeatherApi
import ru.avito.demo.myweather.repository.CityRepository
import ru.avito.demo.myweather.repository.CityRepositoryImpl
import ru.avito.demo.myweather.repository.WeatherRepository
import ru.avito.demo.myweather.repository.WeatherRepositoryImpl
import ru.avito.demo.myweather.ui.city.CityViewModel
import ru.avito.demo.myweather.ui.main.MainViewModel
import java.time.OffsetDateTime

private const val WEATHER_PREF = "weather_pref"

val dependency = module {
    single<WeatherApi> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@single retrofit.create(WeatherApi::class.java)
    }

    single { androidContext().getSharedPreferences(WEATHER_PREF, Context.MODE_PRIVATE) }

    single<CityRepository> { CityRepositoryImpl(get(), get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    viewModel { MainViewModel(get(), get()) }
    viewModel { CityViewModel(get()) }
}