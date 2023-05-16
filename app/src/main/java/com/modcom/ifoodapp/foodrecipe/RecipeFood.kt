package com.modcom.ifoodapp.foodrecipe


import com.google.gson.annotations.SerializedName

data class RecipeFood(
    @SerializedName("results")
    val results: List<Result>,
)