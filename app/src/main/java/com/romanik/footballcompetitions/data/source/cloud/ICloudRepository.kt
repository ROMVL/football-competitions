package com.romanik.footballcompetitions.data.source.cloud

import com.romanik.footballcompetitions.data.rest.model.CompetitionsResponse
import com.romanik.footballcompetitions.data.rest.model.TeamsResponse

interface ICloudRepository {

    suspend fun fetchCompetitions(): CompetitionsResponse

    suspend fun fetchTeams(): TeamsResponse

}