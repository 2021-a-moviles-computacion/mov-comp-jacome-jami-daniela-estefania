package com.example.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText> (R.id.email)
        val login = findViewById<Button>(R.id.login)
        val password = findViewById<EditText>(R.id.password)
        val txt_signup = findViewById<TextView>(R.id.txt_signup)

        login.setOnClickListener  {

            abrirActividad(BottomNavigationMenu::class.java)

        }

    }

    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        // this.startActivity(intent)
        startActivity(intentExplicito)
    }
}