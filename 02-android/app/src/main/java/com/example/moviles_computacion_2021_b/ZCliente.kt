package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class ZCliente : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)



        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)
        if (EBaseDeDatos.TablaUsuario != null) {

            //Entrada texto
            val txtNombre = findViewById<EditText>(R.id.txt_nombreCli)
            val txtId = findViewById<EditText>(R.id.txt_idCli)
            val txtDescripcion = findViewById<EditText>(R.id.txt_descripcionCli)





            //CREAR
            val botonCrear = findViewById<Button>(R.id.btn_create)
            botonCrear.setOnClickListener {
                EBaseDeDatos.TablaUsuario!!.crearUsuarioFormulario(
                    txtNombre.text.toString(),
                    txtDescripcion.text.toString()
                )
                Log.i(
                    "bdd-operations",
                    "Usuario creado ${txtNombre.text} ${txtDescripcion.text}"
                )


            }

            //Actualizar
            val botonActualizar = findViewById<Button>(R.id.btn_update)
            botonActualizar.setOnClickListener {
                EBaseDeDatos.TablaUsuario!!.actualizarUsuarioFormulario(
                    txtNombre.text.toString(),
                    txtDescripcion.text.toString(),
                    txtId.text.toString().toInt()
                )
                Log.i(
                    "bdd-operations",
                    "Usuario actualizado ${txtNombre.text} ${txtDescripcion.text} ${txtId.text}"
                )

            }

            //Eliminar
            val botonEliminar = findViewById<Button>(R.id.btn_delete)
            botonEliminar.setOnClickListener {
                EBaseDeDatos.TablaUsuario!!.eliminarUsuarioFormulario(
                    txtId.text.toString().toInt()
                )
                Log.i("bdd-operations",
                    "Usuario eliminado")

            }

            //Consultar
            val botonConsultar = findViewById<Button>(R.id.btn_search)
            botonConsultar.setOnClickListener {
                val usuario= EBaseDeDatos.TablaUsuario!!.consultarUsuarioPorId(
                    txtId.text.toString().toInt()
                )
                Log.i("bdd-operations", "Usuario ${usuario.nombre_Cli} ")



            }



        }
    }


}


