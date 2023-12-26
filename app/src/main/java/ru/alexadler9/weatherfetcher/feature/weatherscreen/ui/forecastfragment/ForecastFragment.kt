package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexadler9.weatherfetcher.databinding.FragmentForecastBinding
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.WeatherScreenContract

/**
 * Fragment is responsible for loading and displaying the list of daily forecasts for the specified location.
 */
class ForecastFragment : Fragment(), WeatherScreenContract.FragmentCallbacks {

    private val viewModel: ForecastFragmentViewModel by viewModel()
    private var hostCallbacks: WeatherScreenContract.ActivityCallbacks? = null

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostCallbacks = context as WeatherScreenContract.ActivityCallbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        binding.fabWeatherFetch.setOnClickListener {
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
            when (viewState.state) {
                is State.Load -> {
                    layoutError.isVisible = false
                    layoutNotFound.isVisible = false
                    pbWeather.isVisible = true
                    fabWeatherFetch.isEnabled = false
                    layoutWeather.isVisible = false
                }

                is State.Content -> {
                    layoutError.isVisible = false
                    layoutNotFound.isVisible = false
                    pbWeather.isVisible = false
                    fabWeatherFetch.isEnabled = true
                    layoutWeather.isVisible = true
                    val forecast = viewState.state.forecastModel
                    tvWeather.text = forecast.toString()
                }

                is State.Error,
                is State.NotFound -> {
                    layoutError.isVisible = viewState.state is State.Error
                    layoutNotFound.isVisible = viewState.state is State.NotFound
                    pbWeather.isVisible = false
                    fabWeatherFetch.isEnabled = true
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