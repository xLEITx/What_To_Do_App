package com.leit.whattodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_menu)
        bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            if (destination.id == R.id.detailActivityFragment){
                bottomNav.visibility = View.GONE
            }else{
                bottomNav.visibility = View.VISIBLE
            }

        }
    }
}