package com.pat.pokapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PreviewPokemon {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("imgUrl")
    @Expose
    var imgUrl: String? = null
    @SerializedName("pokemonUrl")
    @Expose
    var pokemonUrl: String? = null

}