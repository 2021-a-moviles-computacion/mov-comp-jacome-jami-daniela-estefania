package com.example.instagram.entrenador

import android.os.Parcel
import android.os.Parcelable

class NotificationEntrenador (
    val user: String?,
    val description: String?,
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${user} - ${description}"
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, flag: Int) {

        p0?.writeString(user)
        p0?.writeString(description)

    }

    companion object CREATOR : Parcelable.Creator<NotificationEntrenador> {
        override fun createFromParcel(parcel: Parcel): NotificationEntrenador {
            return NotificationEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<NotificationEntrenador?> {
            return arrayOfNulls(size)
        }
    }
}