package br.com.example.pind.api.caller

import br.com.example.pind.api.services.AuthService
import br.com.example.pind.api.caller.RetrofitFactory

class AuthCaller {
    fun authService() : AuthService {
        return RetrofitFactory().retrofitFactory.create(AuthService::class.java)
    }
}