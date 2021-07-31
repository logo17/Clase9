package com.loguito.clase9.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface PokemonDao {
    @Query("Select * from pokemonentity")
    fun getAllPokemonsByName() : LiveData<List<PokemonEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertPokemon(pokemonEntity: PokemonEntity)

    @Delete
    suspend fun deletePokemon(pokemonEntity: PokemonEntity)
}