package br.com.example.pind

data class Cliente (
    val id: Int,
    val nomeCliente: String,
    val dataPrimeiraCompra: String,
    val dataUltimaCompra: String
)
// TODO adicionar outros campos referentes aos dados do cliente (ex: email, telefone, endereço) como preenchido no popup de add clientes.
// TODO LEMBRAR DE ADICIONAR ESSAS INFORMAÇÕES NA LISTA MOCKADA NO FRAGMENT.