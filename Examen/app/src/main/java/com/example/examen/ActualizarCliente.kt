package com.example.examen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class ActualizarCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cliente)

        BaseDatos.base = SqliteHelperUsuario(this)

        val cli = intent.getParcelableExtra<ClienteEntrenador>("cliente")

        Log.i("bdd", "Editar ${cli}")

        val cedulaCliente = findViewById<EditText>(R.id.etxtCedulaCli)
        val nombreCliente = findViewById<EditText>(R.id.etxtNombreCli)
        val apellidoCliente = findViewById<EditText>(R.id.etxtApellidoCli)
        val correoCliente = findViewById<EditText>(R.id.etxtCorreoCli)
        val fechaNacimientoCliente = findViewById<EditText>(R.id.etxtFechaNacimientoCli)
        val telefonoCliente = findViewById<EditText>(R.id.etxtTelefonoCli)

        val botonAceptar = findViewById<Button>(R.id.btn_actualizar_editar_Cli)

        cedulaCliente.setText(cli?.cedula_cli.toString())
        nombreCliente.setText(cli?.nombre_cli)
        apellidoCliente.setText(cli?.apellido_cli)
        correoCliente.setText(cli?.correo_cli)
        fechaNacimientoCliente.setText(cli?.fechaNacimiento_cli)
        telefonoCliente.setText(cli?.telefono_cli)

        botonAceptar.setOnClickListener {
            if (BaseDatos.base != null) {
                if ((BaseDatos.base?.actualizarCliente(
                        nombreCliente.text.toString(),
                        apellidoCliente.text.toString(),
                        correoCliente.text.toString(),
                        fechaNacimientoCliente.text.toString(),
                        telefonoCliente.text.toString(),
                        cli?.cedula_cli!!.toLong()
                    )) != null
                ) {
                    Log.i(
                        "bd",
                        "Cliente ingresado,  ${cli?.cedula_cli} ${nombreCliente.text.toString()}"
                    )
                }

            }

            cedulaCliente.setText("")
            nombreCliente.setText("")
            apellidoCliente.setText("")
            correoCliente.setText("")
            fechaNacimientoCliente.setText("")
            telefonoCliente.setText("")

            Log.i("bd", BaseDatos.base.toString())
            abrirActividad(MainActivity::class.java)
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
}