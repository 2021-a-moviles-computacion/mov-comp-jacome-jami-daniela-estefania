package com.example.firebaseuno.dto

import android.os.Parcel
import android.os.Parcelable

class FirestoreClienteDto (
    var uid:String?,
    var uid_proveedor:String?,
    var cedula_cli: Long?,
    var ruc_prov_cli: Long?,
    var nombre_cli: String?,
    var apellido_cli: String?,
    var correo_cli:String?,
    var fechaNacimiento_cli: String?,
    var telefono_cli: String?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
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
        return "FirestoreClienteDto(cedula_cli=$cedula_cli, ruc_prov_cli=$ruc_prov_cli, nombre_cli=$nombre_cli, apellido_cli=$apellido_cli, correo_cli=$correo_cli, fechaNacimiento_cli=$fechaNacimiento_cli, telefono_cli=$telefono_cli)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(uid_proveedor)
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

    companion object CREATOR : Parcelable.Creator<FirestoreClienteDto> {
        override fun createFromParcel(parcel: Parcel): FirestoreClienteDto {
            return FirestoreClienteDto(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreClienteDto?> {
            return arrayOfNulls(size)
        }
    }
}