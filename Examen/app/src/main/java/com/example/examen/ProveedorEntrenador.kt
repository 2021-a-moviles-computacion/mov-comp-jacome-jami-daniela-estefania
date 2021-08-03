package com.example.examen

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

class ProveedorEntrenador (
    var ruc_prov: Long?,
    var nombre_prov: String?,
    var ciudad_prov: String?,
    var correo_prov:String?,
    var telefono_prov: String?,
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(ruc_prov)
        parcel.writeString(nombre_prov)
        parcel.writeString(ciudad_prov)
        parcel.writeString(correo_prov)
        parcel.writeString(telefono_prov)
    }

    override fun toString(): String {
        return "${ruc_prov} ${nombre_prov} ${ciudad_prov} ${correo_prov} ${telefono_prov}"
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProveedorEntrenador> {
        override fun createFromParcel(parcel: Parcel): ProveedorEntrenador {
            return ProveedorEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<ProveedorEntrenador?> {
            return arrayOfNulls(size)
        }
    }

}