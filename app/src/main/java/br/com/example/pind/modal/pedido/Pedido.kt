package br.com.example.pind.modal.pedido

data class Pedido(
    val id: Int?,
    val data: String?,
    val cliente: String?,
    val produto: String?,
    val quantidade : String?,
    val valor: Double?
)