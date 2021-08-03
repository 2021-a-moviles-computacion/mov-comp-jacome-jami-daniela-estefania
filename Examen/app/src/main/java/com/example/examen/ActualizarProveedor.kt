package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class ActualizarProveedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_proveedor)

        BaseDatos.base= SqliteHelperUsuario(this)

        val prov = intent.getParcelableExtra<ProveedorEntrenador>("proveedor")

        Log.i("bdd","Editar ${prov}")
        val rucProveedor = findViewById<EditText>(R.id.etxtRUCProv)
        val nombreProveedor= findViewById<EditText>(R.id.etxtNombreProv)
        val ciudadProveedor = findViewById<EditText>(R.id.etxtCiudadProv)
        val correoProveedor = findViewById<EditText>(R.id.etxtCorreoProv)
        val telefonoProveedor = findViewById<EditText>(R.id.etxtTelefonoProv)
        val botonAceptar = findViewById<Button>(R.id.btn_actualizar_editar_Prov)


        rucProveedor.setText(prov!!.ruc_prov.toString())
        nombreProveedor.setText(prov?.nombre_prov)
        ciudadProveedor.setText(prov?.ciudad_prov)
        correoProveedor.setText(prov?.correo_prov)
        telefonoProveedor.setText(prov?.telefono_prov)


        botonAceptar.setOnClickListener{
            if (BaseDatos.base != null){
                if((BaseDatos.base?.actualizarProveedor (
                        nombreProveedor.text.toString(),
                        ciudadProveedor.text.toString(),
                        correoProveedor.text.toString(),
                        telefonoProveedor.text.toString(),
                        prov.ruc_prov!!.toLong()
                    )) !=null){
                    Log.i("bd","proveedoringresado,  ${prov.toString()} ${nombreProveedor.text.toString()}")
                }

            }

            rucProveedor.setText("")
            nombreProveedor.setText("")
            ciudadProveedor.setText("")
            correoProveedor.setText("")
            telefonoProveedor.setText("")

            Log.i("bd",BaseDatos.base.toString())
            abrirActividad(MainActivity::class.java)
        }


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
}




