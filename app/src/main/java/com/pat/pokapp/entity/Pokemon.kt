package com.pat.pokapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Pokemon {
    @SerializedName("base_experience")
    @Expose
    var baseExperience: Int? = null
    @SerializedName("forms")
    @Expose
    var forms: Any? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("is_default")
    @Expose
    var isDefault: Any? = null
    @SerializedName("location_area_encounters")
    @Expose
    var locationAreaEncounters: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("order")
    @Expose
    var order: Any? = null
    @SerializedName("species")
    @Expose
    var species: Species? = null
    @SerializedName("sprites")
    @Expose
    var sprites: String? = null
    @SerializedName("stats")
    @Expose
    var stats: List<Stat>? = null
    @SerializedName("types")
    @Expose
    var types: Any? = null
    @SerializedName("weight")
    @Expose
    var weight: Int? = null

}
