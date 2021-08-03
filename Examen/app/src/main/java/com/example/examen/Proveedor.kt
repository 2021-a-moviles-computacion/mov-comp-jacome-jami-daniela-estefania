package com.example.examen

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class Proveedor : AppCompatActivity() {
    companion object{
        var ruc_proveedor: Long = 0
    }

    var posicionCliSeleccionado = 0
    var adapter: ArrayAdapter<ProveedorEntrenador>? = null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proveedor)

        BaseDatos.base= SqliteHelperUsuario(this)
        val listViewProveedor = findViewById<ListView>(R.id.ltv_proveedor)
        if(BaseDatos.base != null) {
            val proveedores = BaseDatos.base!!.consultarProveedores()
            adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                proveedores
            )

            listViewProveedor.adapter = adapter
        }


        val  botonCrearProveedor = findViewById<Button>(R.id.btn_crearProv)
             botonCrearProveedor.setOnClickListener{
            abrirActividad(CrearProveedor::class.java)
        }
        registerForContextMenu(listViewProveedor)
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
    override fun onCreateContextMenu(

        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_prov, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionCliSeleccionado = id
        ruc_proveedor = adapter!!.getItem(posicionCliSeleccionado)?.ruc_prov!!.toLong()
        Log.i("list-view", "List view ${posicionCliSeleccionado}")
        Log.i("list-view", "Entrenador ${
            BaseDatos.base!!.consultarProveedorPorRuc(ruc_proveedor).ruc_prov} " +
                "${BaseDatos.base!!.consultarProveedorPorRuc(ruc_proveedor).nombre_prov} " )
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var proveedor = adapter!!.getItem(posicionCliSeleccionado)

        return when(item?.itemId){
            // Editar

            R.id.mi_editar_prov -> {
                if( BaseDatos.base != null){
                    abrirActividadParametros(
                        ActualizarProveedor::class.java,
                        proveedor
                    )
                }
                Log.i("list-view","Editar ")
                Log.i("list-view","Entrenador ${BaseDatos.base!!.consultarProveedorPorRuc(
                    ruc_proveedor)}")
                return true
            }

            // Eliminar
            R.id.mi_eliminar_prov -> {
                if( BaseDatos.base != null){
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Eliminar")
                    builder.setMessage("¿Desea eliminar el proveedor seleccionado?")

                    builder.setPositiveButton(
                        "Si",
                        DialogInterface.OnClickListener{
                                dialog, which ->
                            BaseDatos.base!!.eliminarProveedor(ruc_proveedor)
                            adapter?.remove(adapter!!.getItem(posicionCliSeleccionado));
                        }
                    )
                    builder.setNegativeButton("No", null)
                    val eliminar = builder.create()
                    eliminar.show()
                   Log.i("list-view","Eliminar ")
                    Log.i("list-view","Entrenador ${BaseDatos.base!!.consultarProveedorPorRuc(
                        ruc_proveedor)}")
                }

                return true
            }
            R.id.mi_ver_prov -> {
                if (proveedor != null) {
                    abrirActividadParametros(Cliente::class.java,proveedor)
                }
                return true
            }
                else -> super.onContextItemSelected(item)

        }

    }


    fun abrirActividadParametros(
            clase: Class<*>,
            consultar: ProveedorEntrenador?
        ){
            val intentExplicito = Intent(
                this,
                clase
            )
            intentExplicito.putExtra("proveedor",consultar)
            startActivity(intentExplicito)
        }

    }
