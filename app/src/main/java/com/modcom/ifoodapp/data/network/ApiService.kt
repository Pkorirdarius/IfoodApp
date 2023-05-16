package com.modcom.ifoodapp.data.network

import com.modcom.ifoodapp.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("recipes/complexSearch")
    fun getData(): Call<List<MainViewModel>>
}