package com.example.firebaseuno.dto

class FirestoreRestauranteDto(
    var uid:String = "",
    val nombre: String? = null
) {
    override fun toString(): String {
        if( nombre == null)
            return "Restaurante no seleccionado"
        else
            return "${nombre}"
    }
}
