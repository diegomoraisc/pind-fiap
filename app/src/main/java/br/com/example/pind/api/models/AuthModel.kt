package br.com.example.pind.api.models

import com.google.gson.annotations.SerializedName

data class AuthModel (
    @SerializedName("email")                   val email: String,
    @SerializedName("password")                val password: String,
)