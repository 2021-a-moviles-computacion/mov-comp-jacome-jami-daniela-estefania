package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ACicloVida : AppCompatActivity() {

    var numero = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)

        Log.i("ciclo-vida","onCreate")

        val textViewACicloVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        //se cambia el texto al numero convertido en cadena
        textViewACicloVida.text = numero.toString()

        val buttonACicloVida = findViewById<Button>(
            R.id.btn_aumentar_ciclo_vida
        )
        buttonACicloVida.setOnClickListener { aumentarNumero() }
    }
    //suma en uno
    fun aumentarNumero(){
        numero=numero+1
        Log.i("ciclo-vida","numero")
        val textViewACicloVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        textViewACicloVida.text = numero.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            //cargamos
            //cualquier primitivo, solo primitivos
            putInt("numeroGuardado",numero)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado: Int? = savedInstanceState.getInt("numeroGuardado")
        if (numeroRecuperado != null){
            Log.i("ciclo-vida","Llego numero ${numeroRecuperado}")

            numero = numeroRecuperado
            val textViewACicloVida = findViewById<TextView>(
                R.id.txv_ciclo_vida
            )
            textViewACicloVida.text = numero.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida","onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida","onDestroy")
    }


}