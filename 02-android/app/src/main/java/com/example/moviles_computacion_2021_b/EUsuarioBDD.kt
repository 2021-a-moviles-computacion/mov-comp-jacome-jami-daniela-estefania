package com.example.moviles_computacion_2021_b

class EUsuarioBDD(
    var id: Int,
    var nombre: String,
    var descripcion: String?
) {
    var id_cli=id
    var nombre_Cli=nombre
    var descripcion_cli = descripcion

    override fun toString(): String {
        return "${id_cli} ${nombre_Cli} ${descripcion_cli}"
    }
}