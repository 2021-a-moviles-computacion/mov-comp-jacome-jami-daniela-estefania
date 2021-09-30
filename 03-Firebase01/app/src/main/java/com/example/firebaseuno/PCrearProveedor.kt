package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PCrearProveedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pcrear_proveedor)
        val botonCrear = findViewById<Button>(R.id.btn_actualizar_editar_Prov)
        botonCrear
            .setOnClickListener {
                crearProv()
            }
    }

    fun crearProv(){
        val rucProveedor = findViewById<EditText>(R.id.etxtRUCProv)
        val nombreProveedor= findViewById<EditText>(R.id.etxtNombreProv)
        val ciudadProveedor = findViewById<EditText>(R.id.etxtCiudadProv)
        val correoProveedor = findViewById<EditText>(R.id.etxtCorreoProv)
        val telefonoProveedor = findViewById<EditText>(R.id.etxtTelefonoProv)
        val nuevoProveedor = hashMapOf<String, Any>(
            "rucProveedor" to rucProveedor.text.toString().toLong(),
            "nombreProveedor" to nombreProveedor.text.toString(),
            "ciudadProveedor" to ciudadProveedor.text.toString(),
            "correoProveedor" to correoProveedor.text.toString(),
            "telefonoProveedor" to telefonoProveedor.text.toString()
        )
        val db = Firebase.firestore
        val referencia = db.collection("proveedor")
        referencia
            .add(nuevoProveedor)
            .addOnSuccessListener {
                rucProveedor.text.clear()
                nombreProveedor.text.clear()
                ciudadProveedor.text.clear()
                correoProveedor.text.clear()
                telefonoProveedor.text.clear()
            }
            .addOnFailureListener { }


    }
}