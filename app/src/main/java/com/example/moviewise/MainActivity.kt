package com.example.moviewise

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.moviewise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){

                R.id.home_option->{
                    findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                }

                R.id.search_option->{
                    findNavController(R.id.nav_host_fragment).navigate(R.id.searchFragment)
                }
                R.id.favourite_option->{
                    findNavController(R.id.nav_host_fragment).navigate(R.id.favouriteFragment)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private var isBackPressed = false
    override fun onBackPressed() {
        if(findNavController(R.id.nav_host_fragment).currentDestination?.id != R.id.homeFragment ){
            findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
        }else if(isBackPressed){
            finishAffinity()
        }else{
            Toast.makeText(this,"Press back again to exit!",Toast.LENGTH_SHORT).show()
            isBackPressed = true
            Handler(Looper.myLooper()!!).postDelayed({
                isBackPressed = false
            },2000)
        }
    }
}