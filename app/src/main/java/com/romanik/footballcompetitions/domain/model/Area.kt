package com.romanik.footballcompetitions.domain.model

import android.os.Parcel
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import com.romanik.footballcompetitions.presentation.core.platform.KParcelable
import com.romanik.footballcompetitions.presentation.core.platform.parcelableCreator
import com.romanik.footballcompetitions.presentation.core.extentions.emptyString

data class Area(
    @SerializedName("id") @ColumnInfo(name ="id_area") val idArea: Long? = 0L,
    val name: String? = String.emptyString()
) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::Area)
    }

    constructor(parcel: Parcel) : this(parcel.readLong(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeLong(idArea ?: 0L)
            writeString(name)
        }
    }
}