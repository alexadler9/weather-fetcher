package ru.alexadler9.weatherfetcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexadler9.weatherfetcher.databinding.ActivityMainBinding
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.UiEvent
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.ViewState
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.WeatherScreenViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherScreenViewModel by viewModel()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.viewState.observe(this, ::render)

        binding.fabWeatherFetch.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnButtonClicked)
        }
    }

    private fun render(viewState: ViewState) {
        with(binding) {
            pbWeather.isVisible = viewState.isLoading
            tvWeather.text = viewState.temperature
        }
    }
}