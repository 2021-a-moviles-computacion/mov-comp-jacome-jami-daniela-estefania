package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.example.instagram.fragment.Home
import com.example.instagram.fragment.Notification
import com.example.instagram.fragment.Profile
import com.example.instagram.fragment.Search

class BottomNavigationMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navegacion = findViewById<BottomNavigationView>(R.id.bottom_navigation)

       val firstFragment = Home()
        establecerFragmento(firstFragment)
        val secondFragment = Search()
        val thirdFragment = Notification()
        val fourthFragment = Profile()

        if (navegacion != null) {

            navegacion.setOnItemSelectedListener() {
                when (it.itemId) {
                    R.id.nav_home -> establecerFragmento(firstFragment)
                    R.id.nav_search -> establecerFragmento(secondFragment)
                    R.id.nav_heart -> establecerFragmento(thirdFragment)
                    R.id.nav_profile -> establecerFragmento(fourthFragment)
                }
                true
            }
        }
    }

    fun establecerFragmento(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment).commit()
            Log.i("test","establecido")
        }
}