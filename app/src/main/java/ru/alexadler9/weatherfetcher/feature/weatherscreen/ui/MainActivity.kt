package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
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
class MainActivity : AppCompatActivity(), WeatherScreenContract.ActivityCallbacks {

    private lateinit var binding: ActivityMainBinding
    private val fragmentCallbacks: WeatherScreenContract.FragmentCallbacks?
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainerView).let {
            it as WeatherScreenContract.FragmentCallbacks?
        }

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
                fragmentCallbacks?.onCitySearchEdited(text.toString())
            }

            val search = {
                fragmentCallbacks?.onCitySearchButtonClicked()
                etCitySearch.clearFocus()
            }

            etCitySearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH && etCitySearch.text.isNotEmpty()) {
                    search()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

            ivCitySearch.setOnClickListener {
                search()
            }
        }
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, fragment)
        }
    }

    override fun onCitySearchUpdated(text: String) {
        if (binding.etCitySearch.text.toString() != text) {
            binding.etCitySearch.text = text.toEditable()
        }
        binding.ivCitySearch.isEnabled = text.isNotEmpty()
    }
}