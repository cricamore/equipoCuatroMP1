package com.cristian.miniproyecto1.repository

import android.content.Context
import com.cristian.miniproyecto1.model.Pokemon
import com.cristian.miniproyecto1.model.Product
import com.cristian.miniproyecto1.webservice.ApiService
import com.cristian.miniproyecto1.webservice.ApiUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class InventoryRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val apiService: ApiService
) {
    suspend fun getProducts(): MutableList<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts()
                response.pokemonList
            } catch (e: Exception) {
                // Manejar el error
                e.printStackTrace()
                mutableListOf()
            }
        }
    }
}