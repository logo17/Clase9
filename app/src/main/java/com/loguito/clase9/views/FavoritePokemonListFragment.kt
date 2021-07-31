package com.loguito.clase9.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.loguito.clase9.adapter.PokemonListAdapter
import com.loguito.clase9.databinding.FragmentFavoritePokemonBinding
import com.loguito.clase9.viewmodels.FavoritePokemonViewModel

class FavoritePokemonListFragment : Fragment() {
    private var _binding: FragmentFavoritePokemonBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoritePokemonViewModel

    private val adapter = PokemonListAdapter {
        viewModel.delete(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoritePokemonViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritePokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoritePokemonRecyclerView.adapter = adapter

        viewModel.getAllPokemonsByName("").observe(viewLifecycleOwner) {
            adapter.pokemonList = it.map { pokemon -> pokemon.name }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}