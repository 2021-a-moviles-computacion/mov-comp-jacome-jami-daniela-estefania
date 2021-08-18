package com.example.moviles_computacion_2021_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val entrenador = intent.getParcelableExtra<BEntrenador>("entrenador")


        Log.i("intent-explicito", "${nombre}")
        Log.i("intent-explicito", "${apellido}")
        Log.i("intent-explicito", "${edad}")
        Log.i("intent-explicito", "${entrenador}")

        val botonDevolverRespuesta = findViewById<Button>(R.id.btn_devolver_respuesta)

        botonDevolverRespuesta
            .setOnClickListener {
                val intentDevolverParametros = Intent()
                intentDevolverParametros.putExtra("nombreModificado", "Estefania")
                intentDevolverParametros.putExtra("edadModificado", 22)
                intentDevolverParametros.putExtra(
                    "entrenadorModificado",
                    BEntrenador("Estefania", "Jami",null)
                )
//                this.setResult(
                setResult(
                    RESULT_OK,
                    intentDevolverParametros
                )
                finish()//obligatorio si devulvo un resultado
//                this.finish()
            }
    }
}