package com.pat.pokapp.entity.User

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class User(
    var id: Int?,

    var pseudo: String,

    var fbUserId: String
)