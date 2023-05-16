package com.modcom.ifoodapp.data.network

import com.android.volley.Response
import com.modcom.ifoodapp.foodrecipe.RecipeFood
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipeApi {

    @GET("recipes/complexSearch")
    suspend fun getRecipe(
        @QueryMap queries: String
    ):Response<RecipeFood>
}