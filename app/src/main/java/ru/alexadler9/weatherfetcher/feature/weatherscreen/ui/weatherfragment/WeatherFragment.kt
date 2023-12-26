package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexadler9.weatherfetcher.R
import ru.alexadler9.weatherfetcher.databinding.FragmentWeatherBinding
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.WeatherScreenContract

/**
 * Fragment is responsible for loading and displaying the current weather for the specified location.
 */
class WeatherFragment : Fragment(), WeatherScreenContract.FragmentCallbacks {

    private val viewModel: WeatherFragmentViewModel by viewModel()
    private var hostCallbacks: WeatherScreenContract.ActivityCallbacks? = null

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostCallbacks = context as WeatherScreenContract.ActivityCallbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        binding.fabWeatherUpdate.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnUpdateButtonClicked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        hostCallbacks = null
    }

    private fun render(viewState: ViewState) {
        hostCallbacks?.onCitySearchUpdated(viewState.cityEditable)
        with(binding) {
            tvCity.text = viewState.city.uppercase()
            when (viewState.state) {
                is State.Load -> {
                    layoutError.isVisible = false
                    layoutNotFound.isVisible = false
                    pbWeather.isVisible = true
                    fabWeatherUpdate.isEnabled = false
                    tvDateTime.isVisible = false
                    layoutWeather.isVisible = false
                }

                is State.Content -> {
                    layoutError.isVisible = false
                    layoutNotFound.isVisible = false
                    pbWeather.isVisible = false
                    fabWeatherUpdate.isEnabled = true
                    tvDateTime.isVisible = true
                    layoutWeather.isVisible = true
                    val weather = viewState.state.weatherModel
                    tvDateTime.text = weather.datetime
                    tvDescription.text =
                        if (weather.details.isEmpty()) "" else weather.details[0].description.uppercase()
                    tvTemp.text =
                        if (weather.temp.isEmpty()) "" else getString(
                            R.string.temperature_value,
                            weather.temp.toFloat()
                        ).replace(',', '.')
                    tvWindSpeed.text =
                        if (weather.windSpeed.isEmpty()) "" else getString(
                            R.string.wind_speed_value,
                            weather.windSpeed.toFloat()
                        ).replace(',', '.')
                    tvHumidity.text =
                        if (weather.humidity.isEmpty()) "" else getString(
                            R.string.humidity_value,
                            weather.humidity.toInt()
                        )
                    tvTempFeelsLike.text =
                        if (weather.tempFeelsLike.isEmpty()) "" else getString(
                            R.string.temperature_value,
                            weather.tempFeelsLike.toFloat()
                        ).replace(',', '.')
                }

                is State.Error,
                is State.NotFound -> {
                    layoutError.isVisible = viewState.state is State.Error
                    layoutNotFound.isVisible = viewState.state is State.NotFound
                    pbWeather.isVisible = false
                    fabWeatherUpdate.isEnabled = true
                    layoutWeather.isVisible = false
                    tvDateTime.isVisible = false
                    layoutWeather.isVisible = false
                }
            }
        }
    }

    override fun onCitySearchEdited(text: String) {
        viewModel.processUiEvent(UiEvent.OnCitySearchEdited(text))
    }

    override fun onCitySearchButtonClicked() {
        viewModel.processUiEvent(UiEvent.OnCitySearchButtonClicked)
    }
}