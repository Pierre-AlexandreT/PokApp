package com.pat.pokapp.entity

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Species {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

}
