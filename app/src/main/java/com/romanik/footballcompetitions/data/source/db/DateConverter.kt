package com.romanik.footballcompetitions.data.source.db

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun dateToTimeStamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun timeStampToDate(timeStamp: Long?): Date? = if (timeStamp != null) Date(timeStamp) else null

}