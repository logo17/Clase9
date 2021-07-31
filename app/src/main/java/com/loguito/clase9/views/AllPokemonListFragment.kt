package com.loguito.clase9.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.loguito.clase9.adapter.PokemonListAdapter
import com.loguito.clase9.databinding.FragmentAllPokemonBinding
import com.loguito.clase9.viewmodels.AllPokemonViewModel

class AllPokemonListFragment : Fragment() {
    private var _binding: FragmentAllPokemonBinding? = null
    private val binding get() = _binding!!

    private val adapter = PokemonListAdapter {
        viewModel.insert(it)
    }

    private lateinit var viewModel: AllPokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(AllPokemonViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.allPokemonRecyclerView.adapter = adapter

        adapter.pokemonList = dummyList

//        binding.searchEditText.textChanges()
//            .skipInitialValue()
//            .subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .debounce(300, TimeUnit.MILLISECONDS)
//            .doOnNext {
//                adapter.filter.filter(it)
//            }
//            .subscribe()

        binding.searchEditText.addTextChangedListener {
            adapter.filter.filter(it.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val dummyList = listOf("Pikachu", "Bulbasaur", "Charmander")
}