package com.romanik.footballcompetitions.domain.usecase.cloud

import com.romanik.footballcompetitions.data.mapper.CloudErrorMapper
import com.romanik.footballcompetitions.domain.model.Competition
import com.romanik.footballcompetitions.domain.repository.ICompetitionRepository
import com.romanik.footballcompetitions.domain.usecase.base.UseCase

class FetchCompetitionsUseCase(
    errorUtil: CloudErrorMapper,
    private val competitionRepository: ICompetitionRepository
) : UseCase<List<Competition>>(errorUtil) {

    override suspend fun executeOnBackground(): List<Competition> {
        return competitionRepository.fetchCompetitionsFromCloud().competitions ?: emptyList()
    }

}