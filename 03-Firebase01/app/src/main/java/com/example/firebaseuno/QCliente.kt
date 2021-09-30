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
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QCliente : AppCompatActivity() {
    var adapter: ArrayAdapter<FirestoreClienteDto>? = null
    var posicionItemSelecionado = 0

    companion object{
        var cedulaCLiente:Long = 0
    }
    var proveedor: FirestoreProveedorDto?=null
    var arrayClientes = arrayListOf<FirestoreClienteDto>()
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qcliente)

        proveedor = intent.getParcelableExtra<FirestoreProveedorDto>("proveedor")
        Log.i("proveeodor","proveedor ruc: ${proveedor!!.ruc_prov}")

        consultarCliente()

        val botonCrear = findViewById<Button>(R.id.btn_crearCli)
        botonCrear
            .setOnClickListener {
                abrirActividadConParametros(QCrearCliente::class.java,proveedor!!)
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

    fun consultarCliente(){

        val db = Firebase.firestore
        var refproveedor = db
            .collection("cliente")
        refproveedor
            .whereEqualTo("proveedorRuc",proveedor!!.ruc_prov)
            .get()
            .addOnSuccessListener {
                //Log.i("consultas","${it.documents}")
                for (clientes in it){
                    println(clientes)
                    //val ubicacionStore: HashMap<String, *> = clientes.data["ubicacion"] as HashMap<String, *>
                    //Log.i("consultarcliente","${ubicacionStore["latitude"]}")

                    arrayClientes.add(
                        FirestoreClienteDto(
                            clientes.id.toString(),
                            clientes.data["idProveedor"].toString(),
                            clientes.data["cedulaCliente"].toString().toLong(),
                            clientes.data["proveedorRuc"].toString().toLong(),
                            clientes.data["nombreCliente"].toString(),
                            clientes.data["apellidoCliente"].toString(),
                            clientes.data["correoCliente"].toString(),
                            clientes.data["fechaNacimientoCliente"].toString(),
                            clientes.data["telefonoCliente"].toString()
                        )
                    )

                    adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayClientes)
                    val listViewproveedor = findViewById<ListView>(R.id.ltv_clientes)
                    listViewproveedor.adapter = adapter

                    registerForContextMenu(listViewproveedor)

                }

            }
            .addOnFailureListener{}

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
        Log.i("list-view", "Cedula cliwnte ${cedulaCLiente}")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var ClienteSelec = arrayClientes[posicionItemSelecionado]

        return when(item?.itemId){
            // Editar

            R.id.mi_editar_cli -> {
                println("EDITAR")
                abrirActividadConParametros(QEditarCliente::class.java,ClienteSelec,proveedor!!)
                return true
            }

            // Eliminar
            R.id.mi_eliminar_cli -> {
                val db = Firebase.firestore
                var refProv = db
                    .collection("usuario")

                refProv.document(ClienteSelec.uid.toString())
                    .delete()
                    .addOnSuccessListener {
                        adapter?.remove(adapter!!.getItem(posicionItemSelecionado));
                        adapter?.notifyDataSetChanged()
                    }
                    .addOnFailureListener { e -> Log.w("list-view", "Error deleting document", e) }

                return true }


            else -> super.onContextItemSelected(item)
        }

    }
    fun abrirActividadConParametros(//1
        clase: Class<*>,
        proveedor: FirestoreProveedorDto,
    ){
        val intentExplicito = Intent(
            this,
            clase,
        )
        intentExplicito.putExtra("proveedor",proveedor)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }
    fun abrirActividadConParametros(//2
        clase: Class<*>,
        cliente: FirestoreClienteDto,
    ){
        val intentExplicito = Intent(
            this,
            clase,
        )
        intentExplicito.putExtra("cliente",cliente)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }

    fun abrirActividadConParametros(//3
        clase: Class<*>,
        cliente: FirestoreClienteDto,
        proveedor: FirestoreProveedorDto,

    ){
        val intentExplicito = Intent(
            this,
            clase,
        )
        intentExplicito.putExtra("cliente",cliente)
        intentExplicito.putExtra("proveedor",proveedor)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }
}