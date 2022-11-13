package ru.avito.demo.myweather.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import org.threeten.bp.format.DateTimeFormatter
import ru.avito.demo.myweather.R
import ru.avito.demo.myweather.api.model.ForecastWeatherItem
import ru.avito.demo.myweather.databinding.ForecastItemBinding

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    var forecast: List<ForecastWeatherItem> = emptyList()

    private val datePattern = DateTimeFormatter.ofPattern("dd MMM, hh:ss")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val viewBinding = ForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = forecast[position]

        holder.day.text = item.date.format(datePattern)
        holder.temp.text = holder.temp.context.getString(R.string.temp, item.main.temp)

        Glide.with(holder.icon).load(item.weather.first().imageUrl).into(holder.icon)
    }

    override fun getItemCount(): Int  = forecast.size

    class ForecastViewHolder(viewBinding: ForecastItemBinding) : ViewHolder(viewBinding.root) {
        val day = viewBinding.day
        val temp = viewBinding.temp
        val icon = viewBinding.icon
    }
}