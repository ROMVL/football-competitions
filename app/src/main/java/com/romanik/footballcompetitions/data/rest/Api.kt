package com.romanik.footballcompetitions.data.rest

import com.romanik.footballcompetitions.data.rest.model.CompetitionsResponse
import com.romanik.footballcompetitions.data.rest.model.TeamsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {

    @GET("/v2/competitions")
    fun fetchCompetitionsAsync(): Deferred<CompetitionsResponse>

    @GET("/v2/teams")
    fun fetchTeamsAsync(): Deferred<TeamsResponse>

}