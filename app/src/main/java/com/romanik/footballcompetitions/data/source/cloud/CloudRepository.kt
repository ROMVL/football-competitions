package com.romanik.footballcompetitions.data.source.cloud

import com.romanik.footballcompetitions.data.rest.Api
import com.romanik.footballcompetitions.data.rest.model.CompetitionsResponse
import com.romanik.footballcompetitions.data.rest.model.TeamsResponse

class CloudRepository(private val api: Api) : ICloudRepository {

    override suspend fun fetchCompetitions(): CompetitionsResponse = api.fetchCompetitionsAsync().await()

    override suspend fun fetchTeams(): TeamsResponse = api.fetchTeamsAsync().await()

}