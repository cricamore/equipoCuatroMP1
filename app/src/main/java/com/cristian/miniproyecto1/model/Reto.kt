package com.cristian.miniproyecto1.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Reto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val reto: String ): Serializable