package com.pat.pokapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PreviewPokemon {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("sprites")
    @Expose
    var sprites: String? = null

}