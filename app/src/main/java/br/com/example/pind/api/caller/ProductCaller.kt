package br.com.example.pind.api.caller

import android.content.Context
import br.com.example.pind.BuildConfig
import br.com.example.pind.modal.cliente.services.ProductsService
import br.com.example.pind.api.caller.RetrofitFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class ProductsCaller(val context: Context) {
    fun productsService() : Retrofit {
        return RetrofitFactory.retrofitFactor(this.getOkHttpClient(PreferencesHelper(context)))
    }

    private fun getOkHttpClient(preferencesHelper: PreferencesHelper): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .readTimeout(1000, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                addGlobalHeaders(chain, preferencesHelper.getRefreshToken())
            }
            .build()
    }

    private fun addGlobalHeaders(chain: Interceptor.Chain, token: String?): Response {
        var newRequest = chain.request()
        val requestBuilder = newRequest.newBuilder()

        token?.let {
            requestBuilder
                .addHeader("Authorization", "Bearer $it")
                .build()
        }

        newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }


}