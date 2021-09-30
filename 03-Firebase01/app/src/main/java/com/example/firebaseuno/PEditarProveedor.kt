package com.example.firebaseuno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.firebaseuno.dto.FirestoreProveedorDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PEditarProveedor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pcrear_proveedor)
        val proveedor = intent.getParcelableExtra<FirestoreProveedorDto>("proveedor")

        Log.i("bdd", "Editar ${proveedor?.ruc_prov}")
        val rucProveedor = findViewById<EditText>(R.id.etxtRUCProv)
        val nombreProveedor = findViewById<EditText>(R.id.etxtNombreProv)
        val ciudadProveedor = findViewById<EditText>(R.id.etxtCiudadProv)
        val correoProveedor = findViewById<EditText>(R.id.etxtCorreoProv)
        val telefonoProveedor = findViewById<EditText>(R.id.etxtTelefonoProv)
        val botonAceptar = findViewById<Button>(R.id.btn_actualizar_editar_Prov)
        val LatitudProveedor = findViewById<EditText>(R.id.etxtTLatitud)
        val LongitudProveedor = findViewById<EditText>(R.id.etxtLongitud)


        rucProveedor.setText(proveedor?.ruc_prov.toString())
        nombreProveedor.setText(proveedor?.nombre_prov)
        ciudadProveedor.setText(proveedor?.ciudad_prov)
        correoProveedor.setText(proveedor?.correo_prov)
        telefonoProveedor.setText(proveedor?.telefono_prov)
        LatitudProveedor.setText(proveedor?.latitud.toString())
        LongitudProveedor.setText(proveedor?.longitud.toString())




        botonAceptar.isEnabled = true
        botonAceptar
            .setOnClickListener {

                actualizarProveedor()

            }
    }

    fun actualizarProveedor(){
        var proveedor = intent.getParcelableExtra<FirestoreProveedorDto>("proveedor")
        val rucProveedor = findViewById<EditText>(R.id.etxtRUCProv)
        val nombreProveedor = findViewById<EditText>(R.id.etxtNombreProv)
        val ciudadProveedor = findViewById<EditText>(R.id.etxtCiudadProv)
        val correoProveedor = findViewById<EditText>(R.id.etxtCorreoProv)
        val telefonoProveedor = findViewById<EditText>(R.id.etxtTelefonoProv)
        val longitudProveedor = findViewById<EditText>(R.id.etxtLongitud)
        val latitudProveedor = findViewById<EditText>(R.id.etxtTLatitud)

        var proveedorDto = FirestoreProveedorDto(
            null,
            rucProveedor.text.toString().toLong(),
            nombreProveedor.text.toString(),
            ciudadProveedor.text.toString(),
            correoProveedor.text.toString(),
            telefonoProveedor.text.toString(),
                    longitudProveedor.text.toString(),
            latitudProveedor.text.toString())





        val nuevoProveedor = hashMapOf<String, Any>(
            "nombreProveedor" to proveedorDto.nombre_prov!!,
            "ciudadProveedor" to proveedorDto.ciudad_prov!!,
            "correoProveedor" to proveedorDto.correo_prov!!,
            "telefonoProveedor" to proveedorDto.telefono_prov!!,
            "longitudProveedor" to proveedorDto.longitud!!,
            "latitudProveedor" to proveedorDto.latitud!!,

        )

        val db = Firebase.firestore
        val referencia = db.collection("proveedor")
            .document(proveedor?.uid!!)

        db.runTransaction {  transaction ->
            //val documentoActual = transaction.get(referencia)
            transaction.update(referencia, nuevoProveedor)
        }
            .addOnSuccessListener {
                abrirActividad(MainActivity::class.java)
                Log.i("transaccion", "Transaccion Completa")
            }
            .addOnFailureListener{
                Log.i("transaccion", "ERROR")
            }
    }
    private fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent( //Intent es una clase, solamente para que este bien contextualizado.
            this,
            clase
        )
        startActivity(intentExplicito) //Lo heredamos de la clase.
    }

}