package com.loguito.clase9.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class PokemonRepository(private val pokemonDao: PokemonDao) {
    fun getAllPokemonsByName(name: String) : LiveData<List<PokemonEntity>> {
        return pokemonDao.getAllPokemonsByName()
    }

    @WorkerThread
    suspend fun insert(pokemonEntity: PokemonEntity){
        pokemonDao.insertPokemon(pokemonEntity)
    }

    @WorkerThread
    suspend fun delete(pokemonEntity: PokemonEntity){
        pokemonDao.deletePokemon(pokemonEntity)
    }
}