package com.example.picobotella.repository
import android.content.Context
import android.widget.Toast
import com.example.picobotella.data.RetoDB
import com.example.picobotella.data.data.RetoDao
import com.example.picobotella.model.Reto
//import com.example.picobotella.webservice.ApiService
//import com.example.picobotella.webservice.ApiUtils
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

    suspend fun getListRetos():MutableList<Reto>?{
        return withContext(Dispatchers.IO){
            val list = retoDao.getListReto()
            if(list.isEmpty()){
                null
            }else {
                list.reverse()
                list
            }
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
