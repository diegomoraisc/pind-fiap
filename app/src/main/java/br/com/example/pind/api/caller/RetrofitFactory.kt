package br.com.example.pind.api.caller

import br.com.example.pind.api.services.ProductsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    val baseurl: String = "http://10.0.2.2:3333/"

    val retrofitFactory = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
}