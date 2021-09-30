package com.example.firebaseuno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.firebaseuno.dto.FirestoreClienteDto
import com.example.firebaseuno.dto.FirestoreProveedorDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QEditarCliente : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var proveedorIntent: FirestoreProveedorDto?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qcrear_cliente)

        var cliente = intent.getParcelableExtra<FirestoreClienteDto>("cliente")

        val cedulaCliente = findViewById<EditText>(R.id.etxtCedulaCli)
        val nombreCliente = findViewById<EditText>(R.id.etxtNombreCli)
        val apellidoCliente = findViewById<EditText>(R.id.etxtApellidoCli)
        val correoCliente = findViewById<EditText>(R.id.etxtCorreoCli)
        val fechaNacimientoCliente = findViewById<EditText>(R.id.etxtFechaNacimientoCli)
        val telefonoCliente = findViewById<EditText>(R.id.etxtTelefonoCli)

        cedulaCliente.setText(cliente?.cedula_cli.toString())
        nombreCliente.setText(cliente?.nombre_cli)
        apellidoCliente.setText(cliente?.apellido_cli)
        correoCliente.setText(cliente?.correo_cli)
        fechaNacimientoCliente.setText(cliente?.fechaNacimiento_cli)
        telefonoCliente.setText(cliente?.telefono_cli.toString())

        println(cliente)
        val botonCrear = findViewById<Button>(R.id.btn_actualizar_editar_Cli)
        botonCrear
            .setOnClickListener {
                crearCli()
            }
    }

    fun crearCli() {
        var cliente = intent.getParcelableExtra<FirestoreClienteDto>("cliente")

        val cedulaCliente = findViewById<EditText>(R.id.etxtCedulaCli)
        val nombreCliente = findViewById<EditText>(R.id.etxtNombreCli)
        val apellidoCliente = findViewById<EditText>(R.id.etxtApellidoCli)
        val correoCliente = findViewById<EditText>(R.id.etxtCorreoCli)
        val fechaNacimientoCliente = findViewById<EditText>(R.id.etxtFechaNacimientoCli)
        val telefonoCliente = findViewById<EditText>(R.id.etxtTelefonoCli)


        var clienteDto = FirestoreClienteDto(
            null,
        //    proveedorIntent!!.uid.toString(),
            null,
            cedulaCliente.text.toString().toLong(),
//            proveedorIntent!!.ruc_prov.toString().toLong(),
            null,
            nombreCliente.text.toString(),
            apellidoCliente.text.toString(),
            correoCliente.text.toString(),
            fechaNacimientoCliente.text.toString(),
            telefonoCliente.text.toString()
        )

        val nuevoProveedor = hashMapOf<String, Any>(
//            "idProveedor" to clienteDto.uid_proveedor!!,
 //           "proveedorRuc" to clienteDto.ruc_prov_cli!!,
            "cedulaCliente" to clienteDto.cedula_cli!!,
            "nombreCliente" to clienteDto.nombre_cli!!,
            "apellidoCliente" to clienteDto.apellido_cli!!,
            "correoCliente" to clienteDto.correo_cli!!,
            "fechaNacimientoCliente" to clienteDto.fechaNacimiento_cli!!,
            "telefonoCliente" to clienteDto.telefono_cli!!,
        )
        val db = Firebase.firestore
        val referencia = db.collection("cliente")
            .document(cliente?.uid!!)

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