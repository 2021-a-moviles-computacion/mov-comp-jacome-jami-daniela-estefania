package com.example.firebaseuno

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
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.firebaseuno.dto.FirestoreProveedorDto
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Proveedores : AppCompatActivity() {

    var posicionItemSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    var adapter: ArrayAdapter<FirestoreProveedorDto>? = null

    var arrayProveedores = arrayListOf<FirestoreProveedorDto>()


    companion object{
        var ruc_proveedor: Long = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proveedores)


        val botonPCrearProveedor = findViewById<Button>(R.id.btn_crearProv)
        botonPCrearProveedor
            .setOnClickListener {
                abrirActividad(PCrearProveedor::class.java)
            }

        consultarProveedores()
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
    fun consultarProveedores(){

        val db = Firebase.firestore
        var refProveedor = db
            .collection("proveedor")
        refProveedor
            .get()
            .addOnSuccessListener {
                //Log.i("proveedores","${it.documents}")
                for (proveedor in it){
                    Log.i("proveedores","${proveedor.data}")
                    Log.i("proveedores","${proveedor.id}")

                    arrayProveedores.add(
                        FirestoreProveedorDto(
                            proveedor.id,
                            proveedor.data["rucProveedor"].toString().toLong(),
                            proveedor.data["nombreProveedor"].toString(),
                            proveedor.data["ciudadProveedor"].toString(),
                            proveedor.data["correoProveedor"].toString(),
                            proveedor.data["telefonoProveedor"].toString(),
                            proveedor.data["latitudProveedor"].toString(),
                            proveedor.data["longitudProveedor"].toString(),
                            )
                    )



                                adapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        arrayProveedores)
                    val listViewProveedor = findViewById<ListView>(R.id.ltv_proveedor)
                    listViewProveedor.adapter = adapter
                    registerForContextMenu(listViewProveedor)
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
        inflater.inflate(R.menu.menu_prov, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        //val ruc_proveedor = adapter!!.getItem(posicionItemSeleccionado)?.ruc_prov!!.toLong()
        Log.i("list-view", "List view ${posicionItemSeleccionado}")
        Log.i("list-view", "Entrenador ${arrayProveedores[posicionItemSeleccionado].nombre_prov} ${arrayProveedores[posicionItemSeleccionado].correo_prov}")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var ProvSelect = arrayProveedores[posicionItemSeleccionado]


        return when(item?.itemId){
            // Editar

            R.id.mi_editar_prov -> {
                Log.i("list-view","Editar ${ProvSelect.nombre_prov}")
                abrirActividadParametros(PEditarProveedor::class.java,ProvSelect)
                Log.i("list-view","Editar ${ProvSelect.nombre_prov}")
                return true
            }

            // Eliminar
            R.id.mi_eliminar_prov -> {
                println("ELIMINAR"+ProvSelect);
                val clientesEliminar = arrayListOf<String>()
                val db = Firebase.firestore
                var refCli = db
                    .collection("cliente")
                refCli
                    .whereEqualTo("idProveedor",ProvSelect.uid.toString())
                    .get()
                    .addOnSuccessListener {
                        for(cli in it){
                            clientesEliminar.add(cli.id.toString())
                            refCli.document(cli.id).delete()
                                .addOnSuccessListener {
                                    Log.d("list-view", "DocumentSnapshot successfully deleted!")
                                }
                                .addOnFailureListener { e -> Log.w("list-view", "Error deleting document", e) }
                        }
                    }
                    .addOnFailureListener{}



                var refProv = db
                    .collection("proveedor")

                refProv.document(ProvSelect.uid.toString())
                    .delete()
                    .addOnSuccessListener {
                        adapter?.remove(adapter!!.getItem(posicionItemSeleccionado));
                        adapter?.notifyDataSetChanged()
                        Log.d("list-view", "DocumentSnapshot successfully deleted!")



                    }
                    .addOnFailureListener { e -> Log.w("list-view", "Error deleting document", e) }


                return true
            }

            //ver clientes
            R.id.mi_ver_prov -> {
                abrirActividadParametros(QCliente::class.java,ProvSelect)

             //   Log.i("list-view","Ver Cliente ${ProvSelect}")

                return true }
            R.id.mi_ubicacion_prov -> {
                abrirActividadParametros(FMapsActivity::class.java,ProvSelect)
                Log.i("list-view","Ubicacion ${ProvSelect.nombre_prov}")
                return true
            }
            else -> super.onContextItemSelected(item)
            }



        }



    fun abrirActividadParametros(
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
