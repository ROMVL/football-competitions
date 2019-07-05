package com.romanik.footballcompetitions.data.rest.model

import android.os.Parcel
import com.romanik.footballcompetitions.core.platform.KParcelable
import com.romanik.footballcompetitions.core.platform.parcelableCreator
import com.romanik.footballcompetitions.domain.model.Competition

data class CompetitionsResponse(
    val count: Int? = 0,
    val competitions: List<Competition>? = ArrayList()
) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::CompetitionsResponse)
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        arrayListOf<Competition>().apply {
            parcel.readList(this, Competition::class.java.classLoader)
        }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(count ?: 0)
            writeList(competitions)
        }
    }
}