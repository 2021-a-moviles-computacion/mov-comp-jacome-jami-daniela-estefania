package com.example.firebaseuno.dto

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng

class FirestoreProveedorDto (
    var uid:String?,
    var ruc_prov: Long?,
    var nombre_prov: String?,
    var ciudad_prov: String?,
    var correo_prov:String?,
    var telefono_prov: String?,
    var latitud: String?,
    var longitud: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
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
        parcel.writeString(latitud)
        parcel.writeString(longitud)
    }

    override fun describeContents(): Int {
        return 0
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