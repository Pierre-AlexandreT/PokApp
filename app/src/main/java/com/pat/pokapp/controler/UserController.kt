package com.pat.pokapp.controler

import com.pat.pokapp.MyCallback
import com.pat.pokapp.entity.User.User
import com.pat.pokapp.entity.User.Users
import com.pat.pokapp.services.RetrofitPokeApiService
import com.pat.pokapp.services.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserController {

    private var myApiService: RetrofitService

    init {
        val retrofitApi = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8001/web/index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        myApiService = retrofitApi.create(RetrofitService::class.java)


    }

    /**
     * get la liste minimal des pokemons (nom + img)
     */
    fun userIsRegistered(myCallback: MyCallback) {

        val call = myApiService.isRegistered

        call.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.body() != null) {
                    myCallback.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                myCallback.onError(t)
            }
        })
    }

}