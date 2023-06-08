package br.com.example.pind.api.models

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("token")            val token: String,
    @SerializedName("refreshToken")     val refreshToken: String,
    @SerializedName("user")             val user : UserAuthModel
)

