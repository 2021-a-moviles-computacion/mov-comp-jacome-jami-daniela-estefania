package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val arregloNumeros = arrayListOf<Int>(1,2,3)

        val adaptor = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //layour (visual)
            arregloNumeros //arreglo
        )

        val listViewEjemplo = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter=adaptor

        val botonAnadirNumero = findViewById<Button>(R.id.btn_anadir_numero)
        botonAnadirNumero.setOnClickListener {
            anadirItemsAlListView(
                1,
                arregloNumeros,
                adaptor
            )

        }

      /*  listViewEjemplo.setOnItemClickListener { adapterView, view, i, id ->
            Log.i("list-view","Dio click ${i}")
            return@setOnItemClickListener
        }*/

        registerForContextMenu(listViewEjemplo)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
    }
    fun anadirItemsAlListView(
        valor: Int,
        arreglo: ArrayList<Int>,
        adaptador: ArrayAdapter<Int>
    ) {
        arreglo.add(valor)
        adaptador.notifyDataSetChanged() // actualiza la interfaz
    }
}