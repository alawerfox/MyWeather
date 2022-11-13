package ru.avito.demo.myweather.ui.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.avito.demo.myweather.api.model.GeocodingItem
import ru.avito.demo.myweather.databinding.CityItemBinding

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    var geocodingItems: List<GeocodingItem> = emptyList()
    var onClick: (GeocodingItem) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val viewBinding = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val geocodingItem = geocodingItems[position]
        holder.name.text = "${geocodingItem.state}, ${geocodingItem.name}"
        holder.name.setOnClickListener { onClick(geocodingItem) }
    }

    override fun getItemCount(): Int = geocodingItems.size

    class CityViewHolder(viewBinding: CityItemBinding) : ViewHolder(viewBinding.root) {
        val name = viewBinding.root
    }
}