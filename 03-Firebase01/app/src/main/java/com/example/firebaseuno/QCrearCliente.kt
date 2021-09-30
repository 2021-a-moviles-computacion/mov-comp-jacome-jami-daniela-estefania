package com.example.firebaseuno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.firebaseuno.R
import com.example.firebaseuno.dto.FirestoreClienteDto
import com.example.firebaseuno.dto.FirestoreProveedorDto
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QCrearCliente : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var proveedorIntent: FirestoreProveedorDto?=null

    var ubicacionMapa: LatLng? = null
    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qcrear_cliente)

        proveedorIntent = intent.getParcelableExtra<FirestoreProveedorDto>("proveedor")
        Log.i("proveedor","proveedor crear cliente : ${proveedorIntent!!.ruc_prov}")

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

        var clienteDto = FirestoreClienteDto(
            null,
            proveedorIntent!!.uid.toString(),
            cedulaCliente.text.toString().toLong(),
            proveedorIntent!!.ruc_prov.toString().toLong(),
            nombreCliente.text.toString(),
            apellidoCliente.text.toString(),
            correoCliente.text.toString(),
            fechaNacimientoCliente.text.toString(),
            telefonoCliente.text.toString()
        )

        val nuevoProveedor = hashMapOf<String, Any>(
            "idProveedor" to clienteDto.uid_proveedor!!,
            "proveedorRuc" to clienteDto.ruc_prov_cli!!,
            "cedulaCliente" to clienteDto.cedula_cli!!,
            "nombreCliente" to clienteDto.nombre_cli!!,
            "apellidoCliente" to clienteDto.apellido_cli!!,
            "correoCliente" to clienteDto.correo_cli!!,
            "fechaNacimientoCliente" to clienteDto.fechaNacimiento_cli!!,
            "telefonoCliente" to clienteDto.telefono_cli!!,
        )
        val db = Firebase.firestore
        val referencia = db.collection("cliente")
        referencia
            .add(nuevoProveedor)
            .addOnSuccessListener {
                abrirActividadConParametros(QCliente::class.java,proveedorIntent!!)
                cedulaCliente.text.clear()
                nombreCliente.text.clear()
                apellidoCliente.text.clear()
                correoCliente.text.clear()
                fechaNacimientoCliente.text.clear()
                telefonoCliente.text.clear()
            }
            .addOnFailureListener { }


    }

    fun abrirActividadConParametros(
        clase: Class<*>,
        proveedor: FirestoreProveedorDto,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("proveedor",proveedor)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }
}