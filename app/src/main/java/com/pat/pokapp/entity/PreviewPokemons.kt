package com.pat.pokapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PreviewPokemons {

    @SerializedName("results")
    @Expose
    var results: ArrayList<PreviewPokemon>? = null

}