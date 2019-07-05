package com.romanik.footballcompetitions.domain.model

import android.os.Parcel
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.romanik.footballcompetitions.core.extentions.toStringYYYY
import com.romanik.footballcompetitions.core.platform.KParcelable
import com.romanik.footballcompetitions.core.platform.parcelableCreator
import com.romanik.footballcompetitions.core.platform.readDate
import com.romanik.footballcompetitions.core.platform.writeDate
import java.util.*

@Entity
data class Season(
    @SerializedName("id") @ColumnInfo(name ="id_current_season") val id: Long? = 0L,
    val startDate: Date? = Date(),
    val endDate: Date? = Date(),
    @SerializedName("currentMatchday") var currentMatchDay: Int? = 0
) : KParcelable {

    fun getStringFormatDate(date: Date?): String? = date?.toStringYYYY()

    companion object {
        @JvmField val CREATOR = parcelableCreator(::Season)
    }

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readDate(),
        parcel.readDate(),
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeLong(id ?: 0L)
            writeDate(startDate)
            writeDate(endDate)
            writeInt(currentMatchDay ?: 0)
        }
    }
}