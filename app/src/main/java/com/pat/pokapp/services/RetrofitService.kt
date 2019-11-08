package com.pat.pokapp.services

import com.pat.pokapp.entity.Pokemon
import com.pat.pokapp.entity.Pokemons

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @get:GET("pokemons/list/20")
    val pokemons: Call<Pokemons>

    @GET("pokemons/one/{name}")
    fun apiPokemon(@Path("name") name: String?) : Call<Pokemon>


    //    @GET("peoples/{id}")
    //    Call<Contacts.People> getPeople(@Path("id") Integer id);

}
