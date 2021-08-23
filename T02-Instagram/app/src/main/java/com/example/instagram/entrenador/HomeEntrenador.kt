package com.example.instagram.entrenador

import android.os.Parcel
import android.os.Parcelable

class HomeEntrenador(
    val nombre: String?,
    val foto: Int,
    val descripcion: String?,
    val likes: Int,
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }



    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, flag: Int) {

        p0?.writeString(nombre)
        p0?.writeInt(foto)
        p0?.writeString(descripcion)
        p0?.writeInt(likes)

    }

    companion object CREATOR : Parcelable.Creator<HomeEntrenador> {
        override fun createFromParcel(parcel: Parcel): HomeEntrenador {
            return HomeEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<HomeEntrenador?> {
            return arrayOfNulls(size)
        }
    }
}