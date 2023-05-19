package br.com.example.pind

data class ClientesListItem (
    val id: Int,
    val nomeCliente: String,
    val dataPrimeiraCompra: String,
    val dataUltimaCompra: String
)

// TODO Adicionar botão com informações dos clientes