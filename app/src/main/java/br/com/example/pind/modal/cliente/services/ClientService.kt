package br.com.example.pind.modal.cliente.services


import br.com.example.pind.api.models.ClientsModel
import br.com.example.pind.api.models.ProductModel
import retrofit2.Call
import retrofit2.http.*

interface ClientService {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIwMGIyZDU5MS01MTAxLTRiZmQtOWUyMC01Y2JkMzIwOTlkOGMiLCJpYXQiOjE2ODYxNTQ5MTAsImV4cCI6MTY4Njc1OTcxMH0.x16F5b7NWIUpIDpuFlFJ6dR_eiElNepUhWQ-c_y7xMQ")
    @GET("clients")
    fun getProduct() : Call<List<ProductModel>>

    @POST("clients")
    fun postProduct(@Body requestBody: ClientsModel) : Call<ClientsModel>

    @DELETE("clients/{id}")
    fun deleteProduct(@Path(value = "id", encoded = true) id: String) : Call<ClientsModel>

    @PUT("clients/{id}")
    fun putProduct(@Path(value = "id", encoded = true) id: String) : Call<ClientsModel>

}