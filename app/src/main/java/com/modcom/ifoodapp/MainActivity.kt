package com.modcom.ifoodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.modcom.ifoodapp.databinding.ActivityMainBinding
import com.modcom.ifoodapp.ui.favoriteRecipe.FavoriteRecipe
import com.modcom.ifoodapp.ui.favoriteRecipe.fragments.health.Health
import com.modcom.ifoodapp.ui.favoriteRecipe.fragments.health.recipe.Home
import com.modcom.ifoodapp.ui.favoriteRecipe.fragments.health.search.Search


open class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationView.setOnItemReselectedListener {

            when(it.itemId){
                R.id.home2-> openFragment(Home())
                R.id.favoriteRecipe -> openFragment(FavoriteRecipe())
                R.id.search -> openFragment(Search())
                R.id.health ->openFragment(Health())
            }
        }
    }

    private fun openFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.navHostFragment,fragment)
        fragmentTransaction.commit()
    }
}