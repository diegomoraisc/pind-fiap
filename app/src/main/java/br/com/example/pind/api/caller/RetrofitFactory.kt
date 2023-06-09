package br.com.example.pind.api.caller

import br.com.example.pind.modal.cliente.services.ProductsService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {


    companion object {

        val baseurl: String = "http://10.0.2.2:3333/"

        fun  retrofitFactor(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        };

    }


}