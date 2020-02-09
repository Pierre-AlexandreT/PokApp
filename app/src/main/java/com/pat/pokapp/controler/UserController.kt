package com.pat.pokapp.controler

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.pat.pokapp.BuildConfig
import com.pat.pokapp.MyCallback
import com.pat.pokapp.entity.PreviewPokemons
import com.pat.pokapp.entity.User.User
import com.pat.pokapp.entity.User.Users
import com.pat.pokapp.services.RetrofitPokeApiService
import com.pat.pokapp.services.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserController {

    private var myApiService: RetrofitService

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

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofitApi = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8001/web/index.php/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        myApiService = retrofitApi.create(RetrofitService::class.java)




    }

    /**
     * get la liste minimal des pokemons (nom + img)
     */
    fun userIsRegistered(myCallback: MyCallback, fbUserId: String) {

        val call = myApiService.isRegistered(fbUserId)

        call.enqueue(object : Callback<Users> {
            override fun onFailure(call: Call<Users>, t: Throwable) {
                myCallback.onError(t)
            }

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                //if (response.code() != 404) {
                    if (response.body() != null) {
                        myCallback.onSuccess(response.body()!!)
                    }
                //}else{
            }


        })


    }

    fun addUser(myCallback: MyCallback, user: User) {

        val gson = Gson()
        //GsonBuilder().setPrettyPrinting().create() // for pretty print feature

        val jsonUser:String = gson.toJson(user)


        val call = myApiService.saveUser(user)

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                myCallback.onError(t)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body() != null) {
                    myCallback.onSuccess(response.body()!!)
                }
            }

        })
    }

}