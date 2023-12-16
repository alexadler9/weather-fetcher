package ru.alexadler9.weatherfetcher.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepositoryImpl
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherApi
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherRemoteSource
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

const val WEATHER_API_KEY = "63defae2a41ce41a3f41adfd722c0e72"
const val WEATHER_BASE_URL = "https://api.openweathermap.org/"

val networkModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<WeatherApi> { get<Retrofit>().create(WeatherApi::class.java) }

    single<WeatherRemoteSource> { WeatherRemoteSource(get<WeatherApi>()) }

    single<WeatherRepository> { WeatherRepositoryImpl(get<WeatherRemoteSource>()) }

    single<WeatherInteractor> { WeatherInteractor(get<WeatherRepository>()) }
}