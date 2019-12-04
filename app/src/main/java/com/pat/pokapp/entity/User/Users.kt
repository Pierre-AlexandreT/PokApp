package com.pat.pokapp.entity.User

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Users {

    @SerializedName("results")
    @Expose
    var results: ArrayList<User>? = null

}