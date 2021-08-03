package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class CrearProveedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_proveedor)

        BaseDatos.base = SqliteHelperUsuario (this)

        val rucProveedor = findViewById<EditText>(R.id.etxtRUCProv)
        val nombreProveedor= findViewById<EditText>(R.id.etxtNombreProv)
        val ciudadProveedor = findViewById<EditText>(R.id.etxtCiudadProv)
        val correoProveedor = findViewById<EditText>(R.id.etxtCorreoProv)
        val telefonoProveedor = findViewById<EditText>(R.id.etxtTelefonoProv)

        val botonAceptar = findViewById<Button>(R.id.btn_actualizar_editar_Prov)
        botonAceptar.setOnClickListener{
            if (BaseDatos.base != null){
                val proveedor = BaseDatos.base!!.crearProveedor(
                    rucProveedor.text.toString().toLong(),
                    nombreProveedor.text.toString(),
                    ciudadProveedor.text.toString(),
                    correoProveedor.text.toString(),
                    telefonoProveedor.text.toString()
                )
                if(proveedor==true){
                    Log.i("bd","proveedoringresado,  ${rucProveedor.text.toString().toLong()} ${nombreProveedor.text.toString()}")
                    abrirActividad(Proveedor::class.java)
                }else{
                    Log.i("bd","error ingresar proveedor")
                }

            }

            rucProveedor.setText("")
            nombreProveedor.setText("")
            ciudadProveedor.setText("")
            correoProveedor.setText("")
            telefonoProveedor.setText("")
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