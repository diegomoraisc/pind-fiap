package br.com.example.pind

data class VendasListItem(
    val id: Int,
    val data: String,
    val cliente: String,
    val produto: String,
    val quantidade : String,
    val valor: Double
)