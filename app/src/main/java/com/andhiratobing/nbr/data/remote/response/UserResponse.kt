package com.andhiratobing.nbr.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("username")
    @Expose
    val username: String,

    @SerializedName("avatar")
    @Expose
    val avatarUser: String,

    @SerializedName("gender")
    @Expose
    val gender: String
)
