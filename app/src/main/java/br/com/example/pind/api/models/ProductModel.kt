package br.com.example.pind.api.models

import com.google.gson.annotations.SerializedName

data class ProductModel (
        @SerializedName("name")                 val nome: String,
        @SerializedName("price")                val preco: Int,
        @SerializedName("quantity")             val quantidade: Int,
        @SerializedName("unitMeasurement")     val unidade: String,
        )

