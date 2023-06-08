package br.com.example.pind.api.models

import com.google.gson.annotations.SerializedName

data class ClientsModel (
@SerializedName("id")                   val id: String,
@SerializedName("cpf")                  val cpf: String,
@SerializedName("name")                 val nome: String,
@SerializedName("phone")                val fone : String,
@SerializedName("user")                 val user : String,
)