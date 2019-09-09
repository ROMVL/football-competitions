package com.romanik.footballcompetitions.domain.model

import android.os.Parcel
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.romanik.footballcompetitions.presentation.core.platform.KParcelable
import com.romanik.footballcompetitions.presentation.core.platform.parcelableCreator
import com.romanik.footballcompetitions.presentation.core.extentions.emptyString
import com.romanik.footballcompetitions.presentation.core.platform.readDate
import com.romanik.footballcompetitions.presentation.core.platform.writeDate
import java.util.*

@Entity
data class Competition(
    @PrimaryKey(autoGenerate = false) val id: Long? = 0L,
    val name: String? = String.emptyString(),
    val numberOfAvailableSeasons: Int? = 0,
    val code: String? = String.emptyString(),
    val plan: String? = String.emptyString(),
    val lastUpdated: Date? = Date(),
    @Embedded(prefix = "area_") val area: Area? = Area(),
    @Embedded(prefix = "current_season") val currentSeason: Season? = Season()
) : KParcelable {

    companion object {
        @JvmField val CREATOR = parcelableCreator(::Competition)
    }

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDate(),
        parcel.readParcelable(Area::class.java.classLoader),
        parcel.readParcelable(Season::class.java.classLoader)
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeLong(id ?: 0L)
            writeString(name)
            writeString(code)
            writeString(plan)
            writeDate(lastUpdated)
            writeInt(numberOfAvailableSeasons ?: 0)
            writeParcelable(area, flags)
            writeParcelable(currentSeason, flags)
        }
    }

}