package com.pat.pokapp.services

import com.pat.pokapp.entity.Pokemon
import com.pat.pokapp.entity.PokemonName
import com.pat.pokapp.entity.Pokemons

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitPokeApiService {

    @get:GET("https://pokeapi.co/api/v2/pokemon?limit=20")
    val pokemonsList: Call<Pokemons>


    //    @GET("peoples/{id}")
    //    Call<Contacts.People> getPeople(@Path("id") Integer id);

}
