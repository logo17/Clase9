package com.loguito.clase9.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.loguito.clase9.db.PokemonDB
import com.loguito.clase9.db.PokemonEntity
import com.loguito.clase9.db.PokemonRepository
import kotlinx.coroutines.launch

class FavoritePokemonViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var repository: PokemonRepository

    init {
        val db = PokemonDB.getDatabase(application)
        repository = PokemonRepository(db.pokemonDao())
    }

    fun getAllPokemonsByName(name: String): LiveData<List<PokemonEntity>> {
        return repository.getAllPokemonsByName(name)
    }

    fun delete(name: String) = viewModelScope.launch {
        repository.delete(PokemonEntity(name))
    }
}