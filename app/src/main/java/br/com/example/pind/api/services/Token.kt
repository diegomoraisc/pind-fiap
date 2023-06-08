package br.com.example.pind.api.services

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