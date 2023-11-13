package com.example.picobotella.data.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.picobotella.model.Reto

@Dao
interface RetoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReto(reto: Reto)

    @Query("SELECT * FROM Reto")
    suspend fun getListReto(): MutableList<Reto>

    @Delete
    suspend fun deleteReto(reto: Reto)

    @Update
    suspend fun updateReto(reto: Reto)
}