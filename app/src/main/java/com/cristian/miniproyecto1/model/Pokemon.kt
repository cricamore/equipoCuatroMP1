package com.cristian.miniproyecto1.model

import com.google.gson.annotations.SerializedName


data class Pokemon(
    @SerializedName("pokemon")
    val pokemonList: MutableList<Product>,
)
