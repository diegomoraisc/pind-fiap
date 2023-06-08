package br.com.example.pind.api.models

import com.google.gson.annotations.SerializedName

data class UserAuthModel (
    @SerializedName("id")       val id: String,
    @SerializedName("name")     val name: String,
    @SerializedName("email")    val email : UserAuthModel
)