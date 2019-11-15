package com.pat.pokapp.controler

import android.util.Log
import com.google.gson.GsonBuilder
import com.pat.pokapp.MyCallback
import com.pat.pokapp.entity.Pokemon
import com.pat.pokapp.entity.PokemonName
import com.pat.pokapp.entity.Pokemons
import com.pat.pokapp.services.RetrofitService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class PokemonController {

    var service: RetrofitService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8001/web/index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(RetrofitService::class.java)
    }

    fun getPokemons(myCallback: MyCallback) {

        val call = service.pokemons

        call.enqueue(object : Callback<Pokemons> {
            override fun onResponse(call: Call<Pokemons>, response: Response<Pokemons>) {
                if (response.body() != null) {
                    myCallback.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<Pokemons>, t: Throwable) {
                myCallback.onError(t)
            }
        })
    }

    fun getApiPokemon(myCallback: MyCallback, name:String){
        val call = service.apiPokemon(name)

        call.enqueue(object: Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                myCallback.onError(t)
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.body() != null) {
                    myCallback.onSuccess(response.body()!!)
                }
            }

        })
    }

    fun getApiPokemonName(myCallback: MyCallback){
        val call = service.apiPokemonsName

        call.enqueue(object: Callback<PokemonName> {
            override fun onFailure(call: Call<PokemonName>, t: Throwable) {
                myCallback.onError(t)
            }

            override fun onResponse(call: Call<PokemonName>, response: Response<PokemonName>) {
                if (response.body() != null) {

                    myCallback.onSuccess(response.body()!!)
                }
            }

        })
    }
}
