package com.example.moviles_computacion_2021_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Extrae el boton a traves del id
        val botonIrACicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )

        botonIrACicloVida.setOnClickListener {
            abrirCicloVida()
        }
    }

    fun abrirCicloVida(){
        val intentExplicito = Intent(
            this,
            ACicloVida::class.java
        )
        startActivity(intentExplicito)
    }
}

