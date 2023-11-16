package com.cristian.miniproyecto1.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cristian.miniproyecto1.data.RetoDao
import com.cristian.miniproyecto1.model.Reto
import com.cristian.miniproyecto1.utils.Constants.NAME_BD

@Database(entities = [Reto::class], version = 1)
abstract class RetoDB : RoomDatabase() {

    abstract fun retoDao(): RetoDao

    companion object{
        fun getDatabase(context: Context): RetoDB {
            return Room.databaseBuilder(
                context.applicationContext,
                RetoDB::class.java,
                NAME_BD
            ).build()
        }
    }
}