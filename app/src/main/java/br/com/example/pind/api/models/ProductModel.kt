package br.com.example.pind.api.models

import com.google.gson.annotations.SerializedName

data class ProductModel (
        @SerializedName("id")                   val id: String,
        @SerializedName("name")                 val nome: String,
        @SerializedName("price")                val preco: String,
        @SerializedName("quantity")             val quantidade: String,
        @SerializedName("unit_measurement")     val unidade: String,
        @SerializedName("user_id")              val user_id: String,
        )

