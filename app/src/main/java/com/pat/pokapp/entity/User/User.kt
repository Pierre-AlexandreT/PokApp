package com.pat.pokapp.entity.User

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("pseudo")
    @Expose
    var pseudo: String? = null
    @SerializedName("token")
    @Expose
    var token: String? = null

}