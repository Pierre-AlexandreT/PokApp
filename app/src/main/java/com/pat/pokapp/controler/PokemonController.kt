package com.pat.pokapp.controler

import android.util.Log
import com.google.gson.GsonBuilder
import com.pat.pokapp.BuildConfig
import com.pat.pokapp.MyCallback
import com.pat.pokapp.entity.Pokemon
import com.pat.pokapp.entity.PokemonName
import com.pat.pokapp.entity.Pokemons
import com.pat.pokapp.entity.PreviewPokemons
import com.pat.pokapp.services.RetrofitPokeApiService
import com.pat.pokapp.services.RetrofitService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


class PokemonController {

    private var myApiService: RetrofitService
    private var pokeApiService: RetrofitPokeApiService

    init {

        val hli = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            hli.level = HttpLoggingInterceptor.Level.BODY
        } else {
            hli.level = HttpLoggingInterceptor.Level.NONE
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(hli)
            .build()

        val retrofitApi = Retrofit.Builder()
            .baseUrl("http://web/index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        myApiService = retrofitApi.create(RetrofitService::class.java)

        val retrofitPokeApi = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        pokeApiService = retrofitPokeApi.create(RetrofitPokeApiService::class.java)

    }

    fun searchPokemons(myCallback: MyCallback, searchTerm: String) {
        val call = pokeApiService.pokemon(searchTerm)

        call.enqueue(object : Callback<Pokemon> {
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


    /**
     * get la liste minimal des pokemons (nom + img)
     */
    fun getPokemons(myCallback: MyCallback) {

        val call = myApiService.pokemons

        call.enqueue(object : Callback<PreviewPokemons> {
            override fun onResponse(call: Call<PreviewPokemons>, response: Response<PreviewPokemons>) {
                if (response.body() != null) {
                    myCallback.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<PreviewPokemons>, t: Throwable) {
                myCallback.onError(t)
            }
        })
    }

    fun getApiPokemon(myCallback: MyCallback, name: String) {
        val call = myApiService.apiPokemon(name)

        call.enqueue(object : Callback<Pokemon> {
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

    fun getApiPokemonName(myCallback: MyCallback) {
        val call = myApiService.apiPokemonsName

        call.enqueue(object : Callback<PokemonName> {
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

    fun getPokeApiPokemonList(myCallback: MyCallback) {
        val call = pokeApiService.pokemonsList

        call.enqueue(object : Callback<Pokemons> {
            override fun onFailure(call: Call<Pokemons>, t: Throwable) {
                myCallback.onError(t)
            }

            override fun onResponse(call: Call<Pokemons>, response: Response<Pokemons>) {
                if (response.body() != null) {

                    myCallback.onSuccess(response.body()!!)
                }
            }

        })
    }

}
