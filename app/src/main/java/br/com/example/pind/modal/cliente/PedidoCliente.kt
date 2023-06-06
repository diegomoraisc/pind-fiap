package br.com.example.pind.modal.cliente

data class PedidoCliente (
    val id: Int?,
    val nomeCliente: String?,
    val cnpj: String?,
    val email: String?,
    val telefone: String?,
    val cep: String?,
    val rua: String?,
    val bairro: String?,
    val cidade: String?,
    val estado: String?,
    val dataPrimeiraCompra: String? = "",
    val dataUltimaCompra: String? = ""
)