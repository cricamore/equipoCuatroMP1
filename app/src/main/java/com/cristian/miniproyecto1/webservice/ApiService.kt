package com.cristian.miniproyecto1.webservice

import com.cristian.miniproyecto1.model.Product
import com.cristian.miniproyecto1.utils.Constants.END_POINT
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT)
    suspend fun getProducts(): MutableList<Product>
}