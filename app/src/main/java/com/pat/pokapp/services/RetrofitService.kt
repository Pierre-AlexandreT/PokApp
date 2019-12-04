package com.pat.pokapp.services

import com.google.gson.JsonObject
import com.pat.pokapp.entity.*
import com.pat.pokapp.entity.User.User
import com.pat.pokapp.entity.User.Users

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @get:GET("pokemons/all")
    val pokemons: Call<PreviewPokemons>

    @get:GET("pokemons/listname")
    val apiPokemonsName: Call<PokemonName>

    @GET("pokemons/one/{name}")
    fun apiPokemon(@Path("name") name: String?) : Call<Pokemon>

    @GET("pokemons/detail/{name}")
    fun pokemonDetail(@Path("name") name: String?) : Call<Pokemon>

    @GET("pokemons/search/{searchTerm}")
    fun pokemonSearch(@Path("searchTerm") searchTerm: String?) : Call<PreviewPokemons>


    //    @GET("peoples/{id}")
    //    Call<Contacts.People> getPeople(@Path("id") Integer id);

    /************************************************************************/

    @GET("users/one/{fbUserId}")
    fun isRegistered(@Path("fbUserId") fbUserId: String?) : Call<Users>

    @Headers("Accept: application/json")
    @POST("users/save")
    fun saveUser(@Body user: User): Call<User>

}
