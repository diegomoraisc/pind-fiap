package br.com.example.pind.modal.cliente.services

import br.com.example.pind.api.models.AuthModel
import br.com.example.pind.api.models.LoginResponse
import br.com.example.pind.api.models.RegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("sessions")
    fun athenticate(@Body requestBody: AuthModel) : Call<LoginResponse>

    @POST("users")
    fun register(@Body requestBody: RegisterModel) : Call<LoginResponse>

}