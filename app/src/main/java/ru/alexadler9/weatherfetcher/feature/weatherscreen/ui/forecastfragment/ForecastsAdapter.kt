package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.alexadler9.weatherfetcher.R
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.ForecastWeatherModel

class ForecastsAdapter : RecyclerView.Adapter<ForecastsAdapter.ForecastViewHolder>() {

    var data = listOf<ForecastWeatherModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ForecastViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {

        val tvDayOfWeek = rootView.findViewById<TextView>(R.id.tvDayOfWeek)
        val tvDate = rootView.findViewById<TextView>(R.id.tvDate)
        val tvDayTemp = rootView.findViewById<TextView>(R.id.tvDayTemp)
        val tvNightTemp = rootView.findViewById<TextView>(R.id.tvNightTemp)

        fun bind(item: ForecastWeatherModel) {
            tvDayOfWeek.text = item.dayOfWeek.uppercase()
            tvDate.text = item.date
            tvDayTemp.text = rootView.context.getString(
                R.string.temperature_value,
                item.tempDay.toFloat()
            ).replace(',', '.')
            tvNightTemp.text = rootView.context.getString(
                R.string.temperature_value,
                item.tempNight.toFloat()
            ).replace(',', '.')
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): ForecastViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.forecast_item, parent, false) as CardView
                return ForecastViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size
}