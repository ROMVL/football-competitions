package com.romanik.footballcompetitions.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.romanik.footballcompetitions.domain.model.Competition
import com.romanik.footballcompetitions.domain.model.Team

@Database(
    entities = [
        Competition::class,
        Team::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "FootballCompetition.db"
        const val VERSION = 2
    }

    abstract fun competitionDao(): CompetitionDao

    abstract fun teamDao(): TeamDao

}