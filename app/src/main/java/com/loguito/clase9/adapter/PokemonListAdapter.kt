package com.loguito.clase9.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.loguito.clase9.databinding.PokemonItemCellBinding

class PokemonListAdapter(val clickListener: ((String) -> Unit)) : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(),
    Filterable {

    var filteredData: List<String> = emptyList()

    var pokemonList: List<String> = emptyList()
        set(value) {
            field = value
            filteredData = value
            notifyDataSetChanged()
        }

    inner class PokemonViewHolder(private val binding: PokemonItemCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonName: String) {
            binding.pokemonNameTextView.text = pokemonName
            binding.root.setOnClickListener {
                clickListener.invoke(pokemonName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            PokemonItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(filteredData[position])
    }

    override fun getItemCount(): Int = filteredData.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val result = FilterResults()
                result.values = pokemonList.filter { item -> item.lowercase().contains(constraint.toString().lowercase()) }
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredData = results?.values as List<String>
                notifyDataSetChanged()
            }

        }
    }


}