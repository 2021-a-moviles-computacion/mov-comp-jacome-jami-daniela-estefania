package com.example.instagram.entrenador

import android.os.Parcel
import android.os.Parcelable

class PhotosEntrenador (
    val foto: Int
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, flag: Int) {
        p0?.writeInt(foto)

    }

    companion object CREATOR : Parcelable.Creator<PhotosEntrenador> {
        override fun createFromParcel(parcel: Parcel): PhotosEntrenador {
            return PhotosEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<PhotosEntrenador?> {
            return arrayOfNulls(size)
        }
    }
}