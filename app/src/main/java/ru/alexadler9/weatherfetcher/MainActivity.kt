package ru.alexadler9.weatherfetcher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepositoryImpl
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherApiClient
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherRemoteSource
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.WeatherScreenPresenter

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: WeatherScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = WeatherScreenPresenter(
            WeatherInteractor(
                WeatherRepositoryImpl(
                    WeatherRemoteSource(WeatherApiClient.getApi())
                )
            )
        )

        val tvWeather = findViewById<TextView>(R.id.tvWeather)

        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                tvWeather.text = presenter.getWeather()
            }
        }
    }
}