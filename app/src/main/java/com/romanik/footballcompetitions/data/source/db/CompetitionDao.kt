package com.romanik.footballcompetitions.data.source.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.romanik.footballcompetitions.domain.model.Competition

@Dao
interface CompetitionDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCompetitions(competitions: List<Competition>)

    @Delete
    suspend fun deleteCompetition(competition: Competition): Int

    @Query("SELECT * FROM competition")
    suspend fun getAllCompetitions(): List<Competition>

    @Query("SELECT * FROM competition")
    fun getCompetitions(): LiveData<List<Competition>>

}