package com.example.examen

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog

class Cliente : AppCompatActivity() {
    var adapter: ArrayAdapter<ClienteEntrenador>? = null
    var posicionCliSeleccionado = 0

    companion object{
        var cedulaCLiente:Long = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)

        val proveedor= intent.getParcelableExtra<ProveedorEntrenador>("proveedor")

        val ruc = Proveedor.ruc_proveedor
        val nombre = proveedor!!.nombre_prov
        var nombre_proveedor: TextView = findViewById<TextView>(R.id.txvTituloCli)
        nombre_proveedor.setText("Proveedor: ${nombre}")

        Log.i("bdd","ruc:  ${ruc}")

        BaseDatos.base= SqliteHelperUsuario(this)

        if(BaseDatos.base != null) {
            val clientes = BaseDatos.base!!.consultarClientePorProveedor(ruc)
            adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                clientes
            )
            val listViewejemplo = findViewById<ListView>(R.id.ltv_clientes)
            listViewejemplo.adapter = adapter
            registerForContextMenu(listViewejemplo)
        }

        val botoncrearCliente = findViewById<Button>(R.id.btn_crearCli)
        botoncrearCliente.setOnClickListener{
            abrirActividadParametro(CrearCliente::class.java,ruc)
        }
    }

    private fun abrirActividadParametro(clase: Class<*>, ruc: Long) {
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("ruc",ruc)
        startActivity(intentExplicito)
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
        inflater.inflate(R.menu.menu_cli, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionCliSeleccionado = id
        cedulaCLiente = adapter!!.getItem(posicionCliSeleccionado)?.cedula_cli!!.toLong()
        Log.i("list-view", "List view ${posicionCliSeleccionado}")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var cliente = adapter!!.getItem(posicionCliSeleccionado)

        return when(item?.itemId){
            // Editar

            R.id.mi_editar_cli -> {
                if( BaseDatos.base != null){
                    abrirActividadParametros(
                        ActualizarCliente::class.java,
                        cliente
                    )
                }
                Log.i("list-view","Editar ")

                return true
            }

            // Eliminar
            R.id.mi_eliminar_cli -> {
                if( BaseDatos.base != null){
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Eliminar")
                    builder.setMessage("¿Desea eliminar el cliente seleccionado?")

                    builder.setPositiveButton(
                        "Si",
                        DialogInterface.OnClickListener{
                                dialog, which ->
                            BaseDatos.base!!.eliminarCliente(cedulaCLiente)
                            adapter?.remove(adapter!!.getItem(posicionCliSeleccionado));
                        }
                    )
                    builder.setNegativeButton("No", null)
                    val eliminar = builder.create()
                    //abrirActividad(MainActivity::class.java)
                    eliminar.show()
                    Log.i("list-view","Eliminar ")

                }

                return true
            }

            else -> super.onContextItemSelected(item)

        }

    }
    fun abrirActividadParametros(
        clase: Class<*>,
        consultar: ClienteEntrenador?
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("cliente",consultar)
        startActivity(intentExplicito)
    }

}
