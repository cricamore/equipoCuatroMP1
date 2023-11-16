package com.cristian.miniproyecto1.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.miniproyecto1.model.Pokemon
import com.cristian.miniproyecto1.model.Product
import com.cristian.miniproyecto1.repository.InventoryRepository
import com.example.clase8.model.Inventory
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val inventoryRepository: InventoryRepository
): ViewModel(){

    private val _listInventory = MutableLiveData<MutableList<Inventory>>()
    val listInventory: LiveData<MutableList<Inventory>> get() = _listInventory

    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> = _progresState

    //para almacenar una lista de productos
    private val _listProducts = MutableLiveData<MutableList<Product>>()
    val listProducts: LiveData<MutableList<Product>> = _listProducts

    fun getProducts() {
        viewModelScope.launch {
            _progresState.value = true
            try {
                _listProducts.value = inventoryRepository.getProducts()
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }
}