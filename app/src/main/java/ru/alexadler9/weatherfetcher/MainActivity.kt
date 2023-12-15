package ru.alexadler9.weatherfetcher

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.UiEvent
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.ViewState
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.WeatherScreenViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherScreenViewModel by viewModel()

    private val pbWeather: ProgressBar by lazy { findViewById(R.id.pbWeather) }
    private val tvWeather: TextView by lazy { findViewById(R.id.tvWeather) }
    private val fabWeather: FloatingActionButton by lazy { findViewById(R.id.fabWeatherFetch) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.viewState.observe(this, ::render)

        fabWeather.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnButtonClicked)
        }
    }

    private fun render(viewState: ViewState) {
        pbWeather.isVisible = viewState.isLoading
        tvWeather.text = viewState.temperature
    }
}