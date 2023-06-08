package br.com.example.pind.api.services



import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import br.com.example.pind.api.models.ProductModel
import retrofit2.http.Headers

interface ProductsService {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIwMGIyZDU5MS01MTAxLTRiZmQtOWUyMC01Y2JkMzIwOTlkOGMiLCJpYXQiOjE2ODYxNTQ5MTAsImV4cCI6MTY4Njc1OTcxMH0.x16F5b7NWIUpIDpuFlFJ6dR_eiElNepUhWQ-c_y7xMQ")
    @GET("products")
    fun getProduct() : Call<List<ProductModel>>

    @POST("products")
    fun postProduct() : Call<ProductModel>

    @DELETE("products/{id}")
    fun deleteProduct(@Path(value = "id", encoded = true) id: String) : Call<ProductModel>

    @PUT("products/{id}")
    fun putProduct(@Path(value = "id", encoded = true) id: String) : Call<ProductModel>

}