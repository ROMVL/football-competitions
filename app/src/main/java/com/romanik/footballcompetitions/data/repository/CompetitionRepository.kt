package com.romanik.footballcompetitions.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.romanik.footballcompetitions.data.rest.model.CompetitionsResponse
import com.romanik.footballcompetitions.data.source.cloud.ICloudRepository
import com.romanik.footballcompetitions.data.source.db.CompetitionDao
import com.romanik.footballcompetitions.domain.model.Competition
import com.romanik.footballcompetitions.domain.repository.ICompetitionRepository

class CompetitionRepository(
    private val cloudRepository: ICloudRepository,
    private val competitionDao: CompetitionDao
) : ICompetitionRepository {

    override suspend fun fetchCompetitionsFromDB(): List<Competition> = competitionDao.getAllCompetitions()

    override suspend fun fetchCompetitionsFromCloud(): CompetitionsResponse = cloudRepository.fetchCompetitions()

    override suspend fun saveCompetitions(competitions: List<Competition>) = competitionDao.saveCompetitions(competitions)

    override suspend fun fetchCompetitions(): LiveData<List<Competition>> = competitionDao.getCompetitions()

}