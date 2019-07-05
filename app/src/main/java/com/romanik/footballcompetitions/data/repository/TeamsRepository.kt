package com.romanik.footballcompetitions.data.repository

import androidx.lifecycle.LiveData
import com.romanik.footballcompetitions.data.rest.model.TeamsResponse
import com.romanik.footballcompetitions.data.source.cloud.ICloudRepository
import com.romanik.footballcompetitions.data.source.db.TeamDao
import com.romanik.footballcompetitions.domain.model.Team
import com.romanik.footballcompetitions.domain.repository.ITeamRepository

class TeamsRepository(
    private val cloudRepository: ICloudRepository,
    private val teamDao: TeamDao
) : ITeamRepository {
    override suspend fun fetchTeamsFromDB(): List<Team> = teamDao.getAllTeams()

    override suspend fun saveTeams(teams: List<Team>) = teamDao.saveAllTeams(teams)

    override suspend fun fetchTeamsFromCloud(): TeamsResponse = cloudRepository.fetchTeams()
}