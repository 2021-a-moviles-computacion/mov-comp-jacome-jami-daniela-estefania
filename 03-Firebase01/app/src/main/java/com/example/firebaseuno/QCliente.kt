package com.example.firebaseuno

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.firebaseuno.dto.FirestoreClienteDto
import com.example.firebaseuno.dto.FirestoreProveedorDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QCliente : AppCompatActivity() {
    var adapter: ArrayAdapter<FirestoreClienteDto>? = null
    var posicionItemSelecionado = 0

    companion object{
        var cedulaCLiente:Long = 0
    }

    var arrayClientes = arrayListOf<FirestoreClienteDto>()
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var proveedor: FirestoreProveedorDto?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qcliente)

        proveedor= intent.getParcelableExtra<FirestoreProveedorDto>("proveedor")
        Log.i("proveeodor","Proveedor: ${proveedor!!.ruc_prov}")

        consultarCliente()

        val botonCrear = findViewById<Button>(R.id.btn_crearCli)
        botonCrear
            .setOnClickListener {
                abrirActividad(QCrearCliente::class.java)
            }


    }

    fun abrirActividad(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        // this.startActivity(intent)
        startActivity(intentExplicito)
    }

    fun consultarCliente() {

        val db = Firebase.firestore
        var refProveedor = db
            .collection("cliente")
        refProveedor
            .whereEqualTo("uid_proveedor", proveedor!!.uid)
            .get()
            .addOnSuccessListener {
                //Log.i("consultas","${it.documents}")
                for (documentos in it) {
                    /*
                    val ubicacionStore: HashMap<String, *> =
                        documentos.data["ubicacion"] as HashMap<String, *>
*/

                    arrayClientes.add(
                        FirestoreClienteDto(
                            documentos.id,
                            documentos.data["uidProveedor"].toString(),
                            documentos.data["rucProveedor"].toString().toLong(),
                            documentos.data["cedulaCliente"].toString().toLong(),
                            documentos.data["nombreCliente"].toString(),
                            documentos.data["apellidoCliente"].toString(),
                            documentos.data["correoCliente"].toString(),
                            documentos.data["fechaNacimientoCliente"].toString(),
                            documentos.data["telefonoCliente"].toString()
                           /* LatLng(
                                ubicacionStore["latitude"].toString().toDouble(),
                                ubicacionStore["longitude"].toString().toDouble()
                            ),
                            */

                        )
                    )

                    adapter =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayClientes)
                    val listViewCliente = findViewById<ListView>(R.id.ltv_clientes)
                    listViewCliente.adapter = adapter

                    registerForContextMenu(listViewCliente)

                }

            }
            .addOnFailureListener {}

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
        posicionItemSelecionado = id
        cedulaCLiente = adapter!!.getItem(posicionItemSelecionado)?.cedula_cli!!.toLong()
        Log.i("list-view", "List view ${posicionItemSelecionado}")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var cliente = adapter!!.getItem(posicionItemSelecionado)

        return when(item?.itemId){
            // Editar

            R.id.mi_editar_cli -> {
           /*     if( BaseDatos.base != null){
                    abrirActividadParametros(
                        ActualizarCliente::class.java,
                        cliente
                    )
                }
                Log.i("list-view","Editar ")
*/
                return true
            }

            // Eliminar
            R.id.mi_eliminar_cli -> {
          /*      if( BaseDatos.base != null){
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Eliminar")
                    builder.setMessage("¿Desea eliminar el cliente seleccionado?")

                    builder.setPositiveButton(
                        "Si",
                        DialogInterface.OnClickListener{
                                dialog, which ->
                            BaseDatos.base!!.eliminarCliente(cedulaCLiente)
                            adapter?.remove(adapter!!.getItem(posicionItemSelecionado));
                        }
                    )
                    builder.setNegativeButton("No", null)
                    val eliminar = builder.create()
                    //abrirActividad(MainActivity::class.java)
                    eliminar.show()
                    Log.i("list-view","Eliminar ")

                }
*/
                return true
            }

            else -> super.onContextItemSelected(item)

        }

    }
    fun abrirActividadConParametros(
        clase: Class<*>,
        proveedor: FirestoreProveedorDto,
    ){
        val intentExplicito = Intent(
            this,
            clase,
        )
        //intentExplicito.putExtra("nombre","Adrian")
        intentExplicito.putExtra("proveedor",proveedor)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }
}