package br.com.example.pind.modal.pedido

data class PedidosCliente(
    val cliente: String,
    val pedidos: Int,
    val pedidosFinalizados: Int,
    val pedidosAndamentos: Int,
)