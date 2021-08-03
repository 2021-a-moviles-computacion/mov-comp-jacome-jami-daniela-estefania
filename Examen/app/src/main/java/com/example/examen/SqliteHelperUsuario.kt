package com.example.examen

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.text.SimpleDateFormat
import java.util.ArrayList

class SqliteHelperUsuario (
    context: Context
): SQLiteOpenHelper(
    context,"moviles",
    null,
    1
) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaProveedor=
            """
            CREATE TABLE PROV(
                ruc_prov LONG PRIMARY KEY,
                nombre_prov VARCHAR(25),
                ciudad_prov VARCHAR(15),
                correo_prov VARCHAR(25),
                telefono_prov VARCHAR(10)
            );
  
            """.trimIndent()
        Log.i("bdd", "Creacion tabla prov")
        db?.execSQL(scriptCrearTablaProveedor)



        val scriptCrearTablaCliente=
            """
            CREATE TABLE CLI(
                cedula_cli LONG PRIMARY KEY,
                ruc_prov_cli LONG,
                nombre_cli VARCHAR(50),
                apellido_cli varchar(50),
                correo_cli varchar(50),
                fechaNacimiento_cli varchar(50),
                telefono_cli varchar(50),
                foreign key(ruc_prov_cli) references PROV(ruc_prov)
            );
  
            """.trimIndent()
        Log.i("bdd", "Creacion tabla Pelicula")
        db?.execSQL(scriptCrearTablaCliente)


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun crearProveedor(
        ruc: Long,
        nombre: String,
        ciudad: String,
        correo: String,
        telefono: String): Boolean {
        val conexionEscritura= writableDatabase
        val valoresAGuardar= ContentValues()

        valoresAGuardar.put("ruc_prov",ruc)
        valoresAGuardar.put("nombre_prov", nombre)
        valoresAGuardar.put("ciudad_prov", ciudad)
        valoresAGuardar.put("correo_prov", correo)
        valoresAGuardar.put("telefono_prov", telefono)

        val resultadoEscritura: Long= conexionEscritura.insert(
            "PROV",
            null,
            valoresAGuardar)
        conexionEscritura.close()
        if(resultadoEscritura.toInt()!=-1){
            return true
        }else{
            return false
        }
    }

    fun consultarProveedores(): ArrayList<ProveedorEntrenador> {
        val scriptConsultarUsuario = "SELECT * FROM PROV"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario, null)

        val existeUsuario = resultaConsultaLectura.moveToFirst()
        var proveedores = arrayListOf<ProveedorEntrenador>()

        if(existeUsuario){
            do{
                val ruc_prov = resultaConsultaLectura.getLong(0)
                if(ruc_prov!=null){
                    proveedores.add(
                        ProveedorEntrenador(
                            ruc_prov,
                            resultaConsultaLectura.getString(1),
                            resultaConsultaLectura.getString(2),
                            resultaConsultaLectura.getString(3),
                            resultaConsultaLectura.getString(4)
                        ))
                }
            }while(resultaConsultaLectura.moveToNext())
        }

        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return proveedores
    }

    fun actualizarProveedor(
        nombre: String,
        ciudad: String,
        correo: String,
        telefono: String,
        ruc: Long): Boolean {
        val conexionEscritura = writableDatabase
        var valoresActualizar = ContentValues()
        valoresActualizar.put("nombre_prov", nombre)
        valoresActualizar.put("ciudad_prov",ciudad)
        valoresActualizar.put("correo_prov", correo)
        valoresActualizar.put("telefono_prov",telefono)

        val resultadoActualizacion = conexionEscritura.update(
            "PROV",
            valoresActualizar,
            "ruc_prov=?",
            arrayOf(ruc.toString())
        )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true

    }


    fun eliminarProveedor(rucProveedor: Long): Boolean {
        val conexionEscritura = writableDatabase
        var resultadoEliminacion = conexionEscritura
            .delete(
                "PROV",
                "ruc_prov=?",
                arrayOf(rucProveedor.toString())
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun consultarProveedorPorRuc(ruc: Long): ProveedorEntrenador {
        val ruc_2 = ruc.toString()
        val scriptConsultarProveedor = "SELECT * FROM PROV WHERE ruc_prov = ${ruc_2}"
//        val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarProveedor,
            null
        )
        Log.i("bd", "query ejecutado proveedor")
        val existeProveedor = resultaConsultaLectura.moveToFirst()
        // val arregloUsuario = arrayListOf<EUsuarioBDD>()
        val usuarioEncontrado = ProveedorEntrenador(0, "", "","","")
        if (existeProveedor) {
            do {
                val ruc = resultaConsultaLectura.getLong(0) // Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) // Columna indice 1 -> NOMBRE
                val ciudad = resultaConsultaLectura.getString(2)
                val correo = resultaConsultaLectura.getString(3)
                val telefono = resultaConsultaLectura.getString(4)
                if(!ruc.equals(null)){
                    usuarioEncontrado.ruc_prov = ruc
                    usuarioEncontrado.nombre_prov = nombre
                    usuarioEncontrado.ciudad_prov = ciudad
                    usuarioEncontrado.correo_prov = correo
                    usuarioEncontrado.telefono_prov = telefono
                }
            } while (resultaConsultaLectura.moveToNext())

        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado

    }

    fun consultarClientePorProveedor(ruc: Long): ArrayList<ClienteEntrenador> {
        val scriptConsultarCliente = "SELECT * FROM CLI WHERE ruc_prov_cli = ${ruc}"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarCliente,
            null
        )
        val existeCliente = resultaConsultaLectura.moveToFirst()
        val arregloClientes = arrayListOf<ClienteEntrenador>()
        if (existeCliente) {
            do {
                val cedula = resultaConsultaLectura.getLong(0) // Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(2) // Columna indice 1 -> NOMBRE
                val apellido = resultaConsultaLectura.getString(3)
                val correo = resultaConsultaLectura.getString(4)
                val fechaNacimiento = resultaConsultaLectura.getString(5)
                val telefono = resultaConsultaLectura.getString(6)
                if(cedula!=null){
                    arregloClientes.add(
                        ClienteEntrenador(
                            cedula,
                            ruc,
                            nombre,
                            apellido,
                            correo,
                            fechaNacimiento,
                            telefono,
                        )
                    )
                    // arregloUsuario.add(usuarioEncontrado)
                }
            } while (resultaConsultaLectura.moveToNext())

        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloClientes
    }

    fun crearCliente(
        cedula_cli:Long,
        nombre_cli: String,
        apellido_cli: String,
        correo_cli: String,
        fechaNacimiento_cli: String,
        telefono_cli: String,
        ruc_prov_cli: Long): Boolean
    {
        val conexionExcritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("ruc_prov_cli", ruc_prov_cli)
        valoresAGuardar.put("cedula_cli", cedula_cli)
        valoresAGuardar.put("nombre_cli", nombre_cli)
        valoresAGuardar.put("apellido_cli", apellido_cli)
        valoresAGuardar.put("correo_cli", correo_cli)
        valoresAGuardar.put("fechaNacimiento_cli", fechaNacimiento_cli)
        valoresAGuardar.put("telefono_cli", telefono_cli)
        val resultadoEscritura: Long = conexionExcritura
            .insert(
                "CLI",
                null,
                valoresAGuardar
            )
        conexionExcritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun actualizarCliente(nombre :String,
                          apellido:String,
                          correo_cli: String,
                          fechaNacimiento:String,
                          telefono_cli: String,
                          cedula :Long):Boolean {

        val conexionEscritura = writableDatabase
        var valoresActualizar = ContentValues()
        valoresActualizar.put("nombre_cli", nombre)
        valoresActualizar.put("apellido_cli",apellido)
        valoresActualizar.put("correo_cli", correo_cli)
        valoresActualizar.put("fechaNacimiento_cli",fechaNacimiento)
        valoresActualizar.put("telefono_cli",telefono_cli)



        val resultadoActualizacion = conexionEscritura.update(
            "CLI",
            valoresActualizar,
            "cedula_cli=?",
            arrayOf(cedula.toString())
        )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun eliminarCliente(cedulaCLiente: Long): Boolean {
        val conexionEscritura = writableDatabase
        var resultadoEliminacion = conexionEscritura
            .delete(
                "CLI",
                "cedula_cli=?",
                arrayOf(cedulaCLiente.toString())
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }


}
