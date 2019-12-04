package com.pat.pokapp.services

import com.pat.pokapp.entity.*
import com.pat.pokapp.entity.User.User
import com.pat.pokapp.entity.User.Users

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @get:GET("pokemons/list/20")
    val pokemons: Call<Pokemons>

    @get:GET("pokemons/listname")
    val apiPokemonsName: Call<PokemonName>

    @GET("pokemons/one/{name}")
    fun apiPokemon(@Path("name") name: String?) : Call<Pokemon>

    @GET("pokemons/detail/{name}")
    fun pokemonDetail(@Path("name") name: String?) : Call<Pokemon>


    //    @GET("peoples/{id}")
    //    Call<Contacts.People> getPeople(@Path("id") Integer id);

    /************************************************************************/

    @get:GET("users/isRegistered/{token}")
    val isRegistered: Call<Users>
}
