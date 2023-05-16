package com.modcom.ifoodapp.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.modcom.ifoodapp.foodrecipe.Result
import androidx.recyclerview.widget.RecyclerView
import com.modcom.ifoodapp.databinding.RecipeRowLayoutBinding
import com.modcom.ifoodapp.foodrecipe.RecipeFood
import com.modcom.ifoodapp.util.RecipeDiffUtil

class RecipeAdapter(var recipes: List<Result>) : RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {

//    recipes = emptyList<Result>()

    class MyViewHolder(private val binding: RecipeRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }

    companion object {
        fun from(parent: ViewGroup): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecipeRowLayoutBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(binding)
        }
    }
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
    fun setData(newData: RecipeFood){
        val recipeDiffUtil = RecipeDiffUtil(recipes,newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipeDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}