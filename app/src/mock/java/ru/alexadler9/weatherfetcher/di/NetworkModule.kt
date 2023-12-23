package ru.alexadler9.weatherfetcher.di

import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.alexadler9.weatherfetcher.BuildConfig
import ru.alexadler9.weatherfetcher.data.PreferencesRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepositoryImpl
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherApi
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherRemoteSource
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.mock.MockingInterceptor
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.mock.RequestsHandler

val networkModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(MockingInterceptor(get<RequestsHandler>()))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<RequestsHandler> { RequestsHandler(androidContext(), get<PreferencesRepository>()) }

    single<WeatherApi> { get<Retrofit>().create(WeatherApi::class.java) }

    single<WeatherRemoteSource> { WeatherRemoteSource(get<WeatherApi>()) }

    single<WeatherRepository> { WeatherRepositoryImpl(get<WeatherRemoteSource>()) }
}