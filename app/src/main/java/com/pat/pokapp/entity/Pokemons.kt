package com.pat.pokapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Pokemons {

        @SerializedName("results")
        @Expose
        var results: ArrayList<Pokemon>? = null


}