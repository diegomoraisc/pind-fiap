package br.com.example.pind.api.caller

import br.com.example.pind.api.services.ProductsService
import br.com.example.pind.api.caller.RetrofitFactory

class ProductsCaller {
    fun productsService() : ProductsService {
        return RetrofitFactory().retrofitFactory.create(ProductsService::class.java)
    }
}