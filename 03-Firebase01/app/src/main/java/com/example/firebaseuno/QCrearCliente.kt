package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.firebaseuno.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QCrearCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qcrear_cliente)
        val botonCrear = findViewById<Button>(R.id.btn_actualizar_editar_Cli)
        botonCrear
            .setOnClickListener {
                crearCli()
            }
    }

    fun crearCli() {
        val cedulaCliente = findViewById<EditText>(R.id.etxtCedulaCli)
        val nombreCliente = findViewById<EditText>(R.id.etxtNombreCli)
        val apellidoCliente = findViewById<EditText>(R.id.etxtApellidoCli)
        val correoCliente = findViewById<EditText>(R.id.etxtCorreoCli)
        val fechaNacimientoCliente = findViewById<EditText>(R.id.etxtFechaNacimientoCli)
        val telefonoCliente = findViewById<EditText>(R.id.etxtTelefonoCli)
        val nuevoProveedor = hashMapOf<String, Any>(
            "cedulaCliente" to cedulaCliente.text.toString().toLong(),
            "nombreCliente" to nombreCliente.text.toString(),
            "apellidoCliente" to apellidoCliente.text.toString(),
            "correoCliente" to correoCliente.text.toString(),
            "fechaNacimientoCliente" to fechaNacimientoCliente.text.toString(),
            "telefonoCliente" to telefonoCliente.text.toString()
        )
        val db = Firebase.firestore
        val referencia = db.collection("cliente")
        referencia
            .add(nuevoProveedor)
            .addOnSuccessListener {
                cedulaCliente.text.clear()
                nombreCliente.text.clear()
                apellidoCliente.text.clear()
                correoCliente.text.clear()
                fechaNacimientoCliente.text.clear()
                telefonoCliente.text.clear()
            }
            .addOnFailureListener { }


    }
}