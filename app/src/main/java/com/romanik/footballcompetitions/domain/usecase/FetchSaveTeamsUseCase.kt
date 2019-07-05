package com.romanik.footballcompetitions.domain.usecase

import com.romanik.footballcompetitions.data.mapper.CloudErrorMapper
import com.romanik.footballcompetitions.domain.model.Team
import com.romanik.footballcompetitions.domain.repository.ITeamRepository
import com.romanik.footballcompetitions.domain.usecase.base.UseCase

class FetchSaveTeamsUseCase(
    errorMapper: CloudErrorMapper,
    private val teamRepository: ITeamRepository
) : UseCase<List<Team>>(errorMapper) {
    override suspend fun executeOnBackground(): List<Team> {
        var teams = teamRepository.fetchTeamsFromDB()
        if (teams.isEmpty()) {
            teamRepository.saveTeams(teamRepository.fetchTeamsFromCloud().teams)
            teams = teamRepository.fetchTeamsFromDB()
        }
        return teams
    }
}