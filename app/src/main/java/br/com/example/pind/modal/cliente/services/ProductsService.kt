package br.com.example.pind.modal.cliente.services



import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import br.com.example.pind.api.models.ProductModel
import retrofit2.http.Body
import retrofit2.http.Headers

interface ProductsService {
   @GET("products")
    fun getProduct() : Call<List<ProductModel>>

    @POST("products")
    fun postProduct(@Body product:ProductModel) : Call<ProductModel>

    @DELETE("products/{id}")
    fun deleteProduct(@Path(value = "id", encoded = true) id: String) : Call<ProductModel>

    @PUT("products/{id}")
    fun putProduct(@Path(value = "id", encoded = true) id: String) : Call<ProductModel>

}