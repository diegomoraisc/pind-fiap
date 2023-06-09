package br.com.example.pind.modal.cliente.services

class Token {
    companion object {
        private var token: String = "Valor estático"

        var staticToken: String
            get() = token
            set(value) {
                token = value
            }
    }
}