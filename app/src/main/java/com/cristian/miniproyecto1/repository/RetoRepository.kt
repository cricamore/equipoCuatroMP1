package com.cristian.miniproyecto1.repository
import android.content.Context
import android.widget.Toast
import com.cristian.miniproyecto1.data.RetoDB
import com.cristian.miniproyecto1.data.RetoDao
import com.cristian.miniproyecto1.model.Reto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetoRepository(val context: Context){
    private var retoDao: RetoDao = RetoDB.getDatabase(context).retoDao()
    //private var apiService: ApiService = ApiUtils.getApiService()

    suspend fun saveReto(reto:Reto){
        withContext(Dispatchers.IO){
            retoDao.saveReto(reto)
        }
    }

    suspend fun getListRetos():MutableList<Reto>{
        retoDao.saveReto(Reto(id = 0, reto = "Hola Mundo"))
        return withContext(Dispatchers.IO){
            retoDao.getListReto()
        }
    }

    suspend fun deleteReto(inventory: Reto){
        withContext(Dispatchers.IO){
            retoDao.deleteReto(inventory)
        }
    }

    suspend fun updateReto(inventory: Reto){
        withContext(Dispatchers.IO){
            retoDao.updateReto(inventory)
        }
    }

}
