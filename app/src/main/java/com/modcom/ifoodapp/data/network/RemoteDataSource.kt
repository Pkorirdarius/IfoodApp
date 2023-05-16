package com.modcom.ifoodapp.data.network

import com.android.volley.Response
import com.modcom.ifoodapp.foodrecipe.RecipeFood
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipeApi: FoodRecipeApi
){
    suspend fun getRecipes(queries: String): Response<RecipeFood> {
        return foodRecipeApi.getRecipe(queries)
    }
}