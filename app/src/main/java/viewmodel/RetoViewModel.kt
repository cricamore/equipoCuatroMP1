package com.example.picobotella.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.picobotella.data.RetoDB
import com.example.picobotella.model.Reto
import com.example.picobotella.repository.RetoRepository
import kotlinx.coroutines.launch

class RetoViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>()
    private val retoRepository = RetoRepository(context)

    private val _listRetos = MutableLiveData<MutableList<Reto>>()
    val listRetos: LiveData<MutableList<Reto>> get() = _listRetos

    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> = _progresState

    fun saveReto(reto: Reto) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                retoRepository.saveReto(reto)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

    fun getListRetos() {
        viewModelScope.launch {
            _progresState.value = true
            try {
                _listRetos.value = retoRepository.getListRetos()
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

    fun deleteReto(reto: Reto) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                retoRepository.deleteReto(reto)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

    fun updateReto(reto: Reto) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                retoRepository.updateReto(reto)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }
}
