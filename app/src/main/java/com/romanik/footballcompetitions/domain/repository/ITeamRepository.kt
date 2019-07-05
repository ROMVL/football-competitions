package com.romanik.footballcompetitions.domain.repository

import com.romanik.footballcompetitions.data.rest.model.TeamsResponse
import com.romanik.footballcompetitions.domain.model.Team

interface ITeamRepository {

    suspend fun fetchTeamsFromDB(): List<Team>

    suspend fun saveTeams(teams: List<Team>)

    suspend fun fetchTeamsFromCloud(): TeamsResponse

}