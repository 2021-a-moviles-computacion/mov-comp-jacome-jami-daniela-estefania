package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch

class CrearCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cliente)

        BaseDatos.base = SqliteHelperUsuario (this)
        val rucProveedor = intent.getLongExtra("ruc",0)
        println("ruuuuc"+rucProveedor)
        val cedulaCliente = findViewById<EditText>(R.id.etxtCedulaCli)
        val nombreCliente = findViewById<EditText>(R.id.etxtNombreCli)
        val apellidoCliente = findViewById<EditText>(R.id.etxtApellidoCli)
        val correoCliente = findViewById<EditText>(R.id.etxtCorreoCli)
        val fechaNacimientoCliente = findViewById<EditText>(R.id.etxtFechaNacimientoCli)
        val telefonoCliente = findViewById<EditText>(R.id.etxtTelefonoCli)

        val botonAceptar = findViewById<Button>(R.id.btn_actualizar_editar_Cli)

        botonAceptar.setOnClickListener{
            if (BaseDatos.base != null){
                if((BaseDatos.base?.crearCliente (
                        cedulaCliente.text.toString().toLong(),
                        nombreCliente.text.toString(),
                        apellidoCliente.text.toString(),
                        correoCliente.text.toString(),
                        fechaNacimientoCliente.text.toString(),
                        telefonoCliente.text.toString(),
                        rucProveedor
                    )) !=null){
                    Log.i("bd","Cliente ingresado,  ${cedulaCliente.text.toString().toInt()} ${nombreCliente.text.toString()}")
                }

            }

            cedulaCliente.setText("")
            nombreCliente.setText("")
            apellidoCliente.setText("")
            correoCliente.setText("")
            fechaNacimientoCliente.setText("")
            telefonoCliente.setText("")

            Log.i("bd",BaseDatos.base.toString())
            abrirActividad(MainActivity::class.java)
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