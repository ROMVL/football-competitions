package com.romanik.footballcompetitions.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.romanik.footballcompetitions.data.rest.model.CompetitionsResponse
import com.romanik.footballcompetitions.domain.model.Competition

interface ICompetitionRepository {

    suspend fun fetchCompetitionsFromCloud(): CompetitionsResponse

    suspend fun saveCompetitions(competitions: List<Competition>)

    suspend fun fetchCompetitionsFromDB(): List<Competition>

    suspend fun fetchCompetitions(): LiveData<List<Competition>>

}