package com.example.examen

import android.os.Parcel
import android.os.Parcelable

class ClienteEntrenador (
    var cedula_cli: Long?,
    var ruc_prov_cli: Long?,
    var nombre_cli: String?,
    var apellido_cli: String?,
    var correo_cli:String?,
    var fechaNacimiento_cli: String?,
    var telefono_cli: String?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${cedula_cli}  ${ruc_prov_cli} ${nombre_cli} ${apellido_cli} ${correo_cli} ${fechaNacimiento_cli} ${telefono_cli}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(cedula_cli)
        parcel.writeValue(ruc_prov_cli)
        parcel.writeString(nombre_cli)
        parcel.writeString(apellido_cli)
        parcel.writeString(correo_cli)
        parcel.writeString(fechaNacimiento_cli)
        parcel.writeString(telefono_cli)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClienteEntrenador> {
        override fun createFromParcel(parcel: Parcel): ClienteEntrenador {
            return ClienteEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<ClienteEntrenador?> {
            return arrayOfNulls(size)
        }
    }
}
