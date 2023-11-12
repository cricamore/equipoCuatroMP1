package com.cristian.miniproyecto1.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,

    @SerializedName("img")
    val image: String,
)
