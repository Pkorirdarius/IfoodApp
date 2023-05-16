package com.modcom.ifoodapp.ui.favoriteRecipe.fragments.health.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.modcom.ifoodapp.foodrecipe.Result
import com.modcom.ifoodapp.R
import com.modcom.ifoodapp.adpters.RecipeAdapter
import com.modcom.ifoodapp.data.network.ApiService
import com.modcom.ifoodapp.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Home : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter

    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)
        recyclerView = view.findViewById(R.id.recyclerview)
        // Inflate the layout for this fragment
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        val call = apiService.getData()
        call.enqueue(object : Callback<List<MainViewModel>> {
            @Override
            fun onResponse(call: Call<List<MainViewModel>>, response: Response<List<Result>>) {
                if (response.isSuccess) {
                    val data = response.result
                    data?.let {
                        showData(it)
                    }
                } else {
                    // Handle API error
                }
            }

            override fun onFailure(call: Call<List<MainViewModel>>, t: Throwable) {
                // Handle API call failure
            }

            override fun onResponse(
                call: Call<List<MainViewModel>>,
                response: retrofit2.Response<List<MainViewModel>>
            ) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun showData(data: List<com.modcom.ifoodapp.foodrecipe.Result>) {
        adapter = RecipeAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
