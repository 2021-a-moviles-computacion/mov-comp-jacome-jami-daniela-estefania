package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.firebaseuno.dto.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.math.RoundingMode
import java.text.DecimalFormat

class EOrdenes : AppCompatActivity() {

    var arregloRestaurantes = arrayListOf<FirestoreRestauranteDto>()
    var adaptadorRestaurantes: ArrayAdapter<FirestoreRestauranteDto>? = null
    var restauranteSeleccionado: FirestoreRestauranteDto? = null

    var arregloProductos = arrayListOf<FirestoreProductoDto>()
    var adaptadorProductos: ArrayAdapter<FirestoreProductoDto>? = null
    var productoSeleccionado: FirestoreProductoDto? = null

    var arregloOrdenes = arrayListOf<FirestoreOrdenDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eordenes)

        if(adaptadorRestaurantes == null) {
            adaptadorRestaurantes = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arregloRestaurantes
            )
            adaptadorRestaurantes?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarRestaurantes()
        }

        if(adaptadorProductos == null) {
            adaptadorProductos = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arregloProductos
            )
            adaptadorProductos?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarProductos()
        }

        val btnAnadir = findViewById<Button>(R.id.btn_anadir_lista_producto)
        btnAnadir.setOnClickListener {
            cargarListaDeProductos()
        }

        val btnAnadirOrden = findViewById<Button>(R.id.btn_anadir_orden)
        btnAnadirOrden.setOnClickListener {
            crearOrden();
        }
    }


    fun crearOrden(){
        val listViewProductos = findViewById<ListView>(R.id.lv_orden)
        val txtTotal = findViewById<TextView>(R.id.tv_precio)


        val nombreRestaurante = restauranteSeleccionado
        val products = arregloOrdenes
        val nuevaOrden = hashMapOf<String, Any>(
            "nombre" to nombreRestaurante.toString(),
            "products" to products
        )
        val db = Firebase.firestore
        val referencia = db.collection("orden")
        referencia
            .add(nuevaOrden)
            .addOnSuccessListener {
                txtTotal.setText("0.0")
                listViewProductos.adapter = null
            }


    }


    fun cargarRestaurantes(){
        val spinnerRestaurantes = findViewById<Spinner>(R.id.sp_restaurantes)
        spinnerRestaurantes.adapter = adaptadorRestaurantes
        spinnerRestaurantes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                restauranteSeleccionado = arregloRestaurantes[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.i("firebase-firestore", "No seleccionó nada")
            }
        }

        val db = Firebase.firestore

        val referencia = db.collection("restaurante")
            .get()

        referencia
            .addOnSuccessListener{
                for (document in it) {
                    //Log.i("firebase-firestore", "${document.id} => ${document.data}")
                    var restaurante = document.toObject(FirestoreRestauranteDto::class.java)
                    restaurante.uid = document.id
                    arregloRestaurantes.add(restaurante)
                    adaptadorRestaurantes?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener{

            }
    }


    fun cargarProductos(){
        val spinnerProductos = findViewById<Spinner>(R.id.sp_producto)
        spinnerProductos.adapter = adaptadorProductos
        spinnerProductos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                productoSeleccionado = arregloProductos[position]
                println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+productoSeleccionado)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.i("firebase-firestore", "No seleccionó nada")
            }
        }

        val db = Firebase.firestore

        val referencia = db.collection("producto")
            .get()

        referencia
            .addOnSuccessListener{
                for (document in it) {
                    //Log.i("firebase-firestore", "${document.id} => ${document.data}")
                    var producto = document.toObject(FirestoreProductoDto::class.java)
                    producto.uid = document.id
                    arregloProductos.add(producto)
                    adaptadorProductos?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener{

            }
    }

    fun cargarListaDeProductos(){

        val listViewProductos = findViewById<ListView>(R.id.lv_orden)
        val adaptadorOrden = ArrayAdapter(this,android.R.layout.simple_selectable_list_item, arregloOrdenes)
        val txtCantidad = findViewById<TextView>(R.id.et_cantidad_producto)
        val txtTotal = findViewById<TextView>(R.id.tv_precio)

        var precioUnitario: Double = productoSeleccionado?.precio!!
        val cantidad = txtCantidad.text.toString().toInt()
        var totalUnitario = precioUnitario * cantidad
        val total = String.format("%.2f", totalUnitario)
        var totalOrden = 0.0



        listViewProductos.adapter = adaptadorOrden

        arregloOrdenes.add(FirestoreOrdenDto(
            productoSeleccionado!!.nombre,
            productoSeleccionado?.precio!!,
            cantidad,
            total.toDouble() ))

        txtCantidad.text = ""

        if(arregloOrdenes != null){
            totalOrden  = arregloOrdenes.map { it!!.total}
                .fold(0.0) { acumulador, totalUnitarioSuma -> (acumulador + totalUnitarioSuma!!) }.toDouble()
        }

        txtTotal.text = "$${totalOrden.toString()}"
        adaptadorOrden.notifyDataSetChanged()
    }

}
