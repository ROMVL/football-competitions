package com.romanik.footballcompetitions.di

import android.app.Application
import androidx.room.Room
import com.romanik.footballcompetitions.data.source.db.AppDatabase
import com.romanik.footballcompetitions.data.source.db.CompetitionDao
import com.romanik.footballcompetitions.data.source.db.TeamDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val databaseModule = module {
    single { providesDatabase(androidApplication()) }
    single { providesCompetitionDao(get()) }
    single { providesTeamDao(get()) }
}

fun providesDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
        //.fallbackToDestructiveMigration()
        .build()
}

fun providesCompetitionDao(appDatabase: AppDatabase): CompetitionDao = appDatabase.competitionDao()

fun providesTeamDao(appDatabase: AppDatabase): TeamDao = appDatabase.teamDao()