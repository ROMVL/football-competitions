package com.romanik.footballcompetitions.domain.model

import android.os.Parcel
import com.romanik.footballcompetitions.presentation.core.extentions.emptyString
import com.romanik.footballcompetitions.presentation.core.platform.KParcelable
import com.romanik.footballcompetitions.presentation.core.platform.parcelableCreator
import com.romanik.footballcompetitions.presentation.core.platform.readDate
import com.romanik.footballcompetitions.presentation.core.platform.writeDate
import java.util.*

data class Player(
    val id: Long? = 0L,
    val name: String? = String.emptyString(),
    val position: String? = String.emptyString(),
    val dateOfBirth: Date? = Date(),
    val countryOfBirth: String? = String.emptyString(),
    val nationality: String? = String.emptyString(),
    val role: String? = String.emptyString()
) : KParcelable {

    companion object {
        @JvmField val CREATOR = parcelableCreator(::Player)
    }

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDate(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeLong(id ?: 0L)
            writeString(name)
            writeString(position)
            writeDate(dateOfBirth)
            writeString(countryOfBirth)
            writeString(nationality)
            writeString(role)
        }
    }
}