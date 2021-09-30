package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PCrearProveedor : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var destino = LatLng(0.00000,0.0000 )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pcrear_proveedor)
        val botonCrear = findViewById<Button>(R.id.btn_actualizar_editar_Prov)
        botonCrear
            .setOnClickListener {
                crearProv()

                /*val fragmentoMapa = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
                fragmentoMapa.getMapAsync { googleMap ->
                    if (googleMap != null) {
                        mapa = googleMap
                        val zoom = 17f
                        println(destino)
                        moverCamaraConZoom(destino, zoom)
                        anadirMarcador(destino)
                    }
                }*/
            }
            }



    fun crearProv() {
        val rucProveedor = findViewById<EditText>(R.id.etxtRUCProv)
        val nombreProveedor = findViewById<EditText>(R.id.etxtNombreProv)
        val ciudadProveedor = findViewById<EditText>(R.id.etxtCiudadProv)
        val correoProveedor = findViewById<EditText>(R.id.etxtCorreoProv)
        val telefonoProveedor = findViewById<EditText>(R.id.etxtTelefonoProv)
        val longitudProveedor = findViewById<EditText>(R.id.etxtLongitud)
        val latitudProveedor = findViewById<EditText>(R.id.etxtTLatitud)
        val nuevoProveedor = hashMapOf<String, Any>(
            "rucProveedor" to rucProveedor.text.toString().toLong(),
            "nombreProveedor" to nombreProveedor.text.toString(),
            "ciudadProveedor" to ciudadProveedor.text.toString(),
            "correoProveedor" to correoProveedor.text.toString(),
            "telefonoProveedor" to telefonoProveedor.text.toString(),
            "longitudProveedor" to longitudProveedor.text.toString()!!,
            "latitudProveedor" to latitudProveedor.text.toString()!!,

            )
        println("**************************")
        val latitud = nuevoProveedor["longitudProveedor"].toString().toDouble()
        val longitud = nuevoProveedor["latitudProveedor"].toString().toDouble()
        println(latitud)
        println(longitud)
        val n1 =latitud
        val n2 = longitud
        destino = LatLng(n1,n2)
        println("1*********"+destino)
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
                longitudProveedor.text.clear()
                latitudProveedor.text.clear()
            }
            .addOnFailureListener { }


    }
    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        println(latLng);
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun anadirMarcador(latLng: LatLng){
        mapa.addMarker(
            MarkerOptions()
                .position(latLng)
        )
    }


}