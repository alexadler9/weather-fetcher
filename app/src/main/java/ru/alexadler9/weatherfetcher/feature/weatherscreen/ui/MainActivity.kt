package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.alexadler9.weatherfetcher.R
import ru.alexadler9.weatherfetcher.base.hideKeyboard
import ru.alexadler9.weatherfetcher.base.toEditable
import ru.alexadler9.weatherfetcher.databinding.ActivityMainBinding
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment.ForecastFragment
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment.WeatherFragment

/**
 * The activity controls weather/forecast fragments. Also responsible for shared elements such as:
 * - location input field;
 * - location search button.
 */
class MainActivity : AppCompatActivity(), WeatherScreenContract.Activity {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.weatherFragment -> {
                        selectTab(WeatherFragment())
                    }
                    R.id.forecastFragment -> {
                        selectTab(ForecastFragment())
                    }
                    else -> {}
                }
                true
            }

            etCitySearch.setOnFocusChangeListener { view, hasFocus ->
                if (!hasFocus) {
                    hideKeyboard()
                }
            }

            etCitySearch.doAfterTextChanged { text ->
                supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                    .let { fragment ->
                        if (fragment is WeatherScreenContract.Fragment) {
                            fragment.onCitySearchEditChanged(text.toString())
                        }
                    }
            }

            ivCitySearch.setOnClickListener {
                supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                    .let { fragment ->
                        if (fragment is WeatherScreenContract.Fragment) {
                            fragment.onCitySearchButtonClicked()
                            etCitySearch.clearFocus()
                        }
                    }
            }
        }
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, fragment)
        }
    }

    override fun citySearchEditSetText(text: String) {
        if (binding.etCitySearch.text.toString() != text) {
            binding.etCitySearch.text = text.toEditable()
        }
    }

    override fun citySearchButtonSetEnabled(enabled: Boolean) {
        binding.ivCitySearch.isEnabled = enabled
    }
}