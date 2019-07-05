package com.romanik.footballcompetitions.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.romanik.footballcompetitions.domain.model.Team

@Dao
interface TeamDao {

    @Query("SELECT * FROM team")
    suspend fun getAllTeams(): List<Team>

    @Transaction
    @Insert
    suspend fun saveAllTeams(teams: List<Team>)

}