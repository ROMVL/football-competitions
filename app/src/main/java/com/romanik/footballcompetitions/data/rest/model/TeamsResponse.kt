package com.romanik.footballcompetitions.data.rest.model

import android.os.Parcel
import com.romanik.footballcompetitions.core.platform.KParcelable
import com.romanik.footballcompetitions.core.platform.parcelableCreator
import com.romanik.footballcompetitions.domain.model.Team

data class TeamsResponse(
    val count: Int? = 0,
    val teams: List<Team> = arrayListOf()
) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::TeamsResponse)
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        arrayListOf<Team>().apply {
            parcel.readList(this, Team::class.java.classLoader)
        }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(count ?: 0)
            writeList(teams)
        }
    }
}