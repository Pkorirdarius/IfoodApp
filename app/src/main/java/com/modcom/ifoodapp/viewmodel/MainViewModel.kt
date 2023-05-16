package com.modcom.ifoodapp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.volley.Response
import com.modcom.ifoodapp.data.network.Respository
import com.modcom.ifoodapp.foodrecipe.RecipeFood
import com.modcom.ifoodapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val respository: Respository,
    application: Application
) : AndroidViewModel(application) {

    private var recipeResponse: MutableLiveData<NetworkResult<RecipeFood>> = MutableLiveData()

    fun getRecipes() = viewModelScope.launch {
        getRecipeSafeCall(queries="")
    }

    private suspend fun getRecipeSafeCall(queries: String){
        recipeResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = respository.remote.getRecipes(queries)
                recipeResponse.value = handleFoodRecipeResponse(response)
            }catch (e: java.lang.Exception ){
                recipeResponse.value = NetworkResult.Error("recipe not found")
            }
        } else {
            recipeResponse.value = NetworkResult.Error("No internet connection")
        }
    }

    private fun handleFoodRecipeResponse(response: Response<RecipeFood>): NetworkResult<RecipeFood>? {

        when {
            response.toString().contains("timeout") ->{
                return NetworkResult.Error("Timeout")
            }
            response.hashCode() == 402 -> {
                return NetworkResult.Error("API key Limited")
            }
            response.result == null -> {
                return NetworkResult.Error("Recipes not found")
            }
            response.isSuccess -> {
                val foodRecipe = response.result
                return if (foodRecipe != null) {
                    NetworkResult.Success(foodRecipe)
                } else {
                    NetworkResult.Error("Response body is null")
                }
            }
            else -> {
                return NetworkResult.Error(response.toString())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {

        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
            else -> false
        }
    }
}

