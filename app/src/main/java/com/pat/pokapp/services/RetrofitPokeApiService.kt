package com.pat.pokapp.services

import com.pat.pokapp.entity.Pokemon
import com.pat.pokapp.entity.PokemonName
import com.pat.pokapp.entity.Pokemons
import com.pat.pokapp.entity.PreviewPokemons

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitPokeApiService {

    @get:GET("pokemon?limit=20")
    val pokemonsList: Call<Pokemons>

    @GET("pokemon/{searchPokemon}")
    fun pokemon(@Path("searchPokemon") searchPokemon: String?): Call<Pokemon>


}
