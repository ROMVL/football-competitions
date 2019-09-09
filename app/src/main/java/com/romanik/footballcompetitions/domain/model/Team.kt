package com.romanik.footballcompetitions.domain.model

import android.os.Parcel
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.romanik.footballcompetitions.presentation.core.extentions.emptyString
import com.romanik.footballcompetitions.presentation.core.platform.KParcelable
import com.romanik.footballcompetitions.presentation.core.platform.parcelableCreator

@Entity
data class Team(
    @PrimaryKey(autoGenerate = false) val id: Long? = 0L,
    val name: String? = String.emptyString(),
    val shortName: String? = String.emptyString(),
    val tla: String? = String.emptyString(),
    val address: String? = String.emptyString(),
    val phone: String? = String.emptyString(),
    val website: String? = String.emptyString(),
    val email: String? = String.emptyString(),
    val clubColors: String? = String.emptyString(),
    val venue: String? = String.emptyString(),
    @SerializedName("crestUrl") val imageUrl: String? = String.emptyString(),
    val founded: Int? = 0,
    //@Embedded(prefix = "squad_") val squad: List<Player>? = arrayListOf(),
    @Embedded(prefix = "area_") val area: Area? = Area()
) : KParcelable {

    companion object {
        @JvmField val CREATOR = parcelableCreator(::Team)
    }

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        //arrayListOf<Player>().apply { parcel.readList(this, Player::class.java.classLoader) },
        parcel.readParcelable(Area::class.java.classLoader)
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeLong(id ?: 0L)
            writeString(name)
            writeString(shortName)
            writeString(tla)
            writeString(address)
            writeString(phone)
            writeString(website)
            writeString(email)
            writeString(clubColors)
            writeString(venue)
            writeString(imageUrl)
            writeInt(founded ?: 0)
            //writeList(squad)
            writeParcelable(area, flags)
        }
    }

}