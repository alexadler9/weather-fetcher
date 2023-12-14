package ru.alexadler9.weatherfetcher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.WeatherScreenPresenter

class MainActivity : AppCompatActivity() {

    private val presenter: WeatherScreenPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvWeather = findViewById<TextView>(R.id.tvWeather)

        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                tvWeather.text = presenter.getWeather()
            }
        }
    }
}