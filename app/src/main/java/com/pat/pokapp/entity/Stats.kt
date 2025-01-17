package com.pat.pokapp.entity

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Stats {
    @SerializedName("base_stat")
    @Expose
    var baseStat: Int? = null
    @SerializedName("effort")
    @Expose
    var effort: Int? = null
    @SerializedName("stat")
    @Expose
    var stat: Stat? = null

}

class Stat {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

}