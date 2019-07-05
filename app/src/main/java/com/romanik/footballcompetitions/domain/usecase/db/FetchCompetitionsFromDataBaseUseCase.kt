package com.romanik.footballcompetitions.domain.usecase.db

import androidx.lifecycle.LiveData
import com.romanik.footballcompetitions.data.mapper.CloudErrorMapper
import com.romanik.footballcompetitions.domain.model.Competition
import com.romanik.footballcompetitions.domain.repository.ICompetitionRepository
import com.romanik.footballcompetitions.domain.usecase.base.UseCase

class FetchCompetitionsFromDataBaseUseCase(
    errorUtil: CloudErrorMapper,
    private val competitionRepository: ICompetitionRepository
) : UseCase<LiveData<List<Competition>>>(errorUtil) {

    override suspend fun executeOnBackground(): LiveData<List<Competition>> {
        return competitionRepository.fetchCompetitions()
    }

}