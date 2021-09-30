package com.example.firebaseuno.dto

import android.os.Parcel
import android.os.Parcelable

class FirestoreProveedorDto (
    var uid:String?,
    var ruc_prov: Long?,
    var nombre_prov: String?,
    var ciudad_prov: String?,
    var correo_prov:String?,
    var telefono_prov: String?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeValue(ruc_prov)
        parcel.writeString(nombre_prov)
        parcel.writeString(ciudad_prov)
        parcel.writeString(correo_prov)
        parcel.writeString(telefono_prov)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "FirestoreProveedorDto(uid=$uid, ruc_prov=$ruc_prov, nombre_prov=$nombre_prov, ciudad_prov=$ciudad_prov, correo_prov=$correo_prov, telefono_prov=$telefono_prov)"
    }

    companion object CREATOR : Parcelable.Creator<FirestoreProveedorDto> {
        override fun createFromParcel(parcel: Parcel): FirestoreProveedorDto {
            return FirestoreProveedorDto(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreProveedorDto?> {
            return arrayOfNulls(size)
        }
    }
}