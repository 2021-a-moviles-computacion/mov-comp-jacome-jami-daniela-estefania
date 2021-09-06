package com.example.firebaseuno.dto

class FirestoreOrdenDto (
    val nombre_producto: String?,
    val precio_producto: Double?,
    val cantidad_producto: Int?,
    val total: Double?
) {
    override fun toString(): String {

        return " ${nombre_producto}\t\t" +
                "${precio_producto}\t ${cantidad_producto}\t " +
                "${total}"

    }
}