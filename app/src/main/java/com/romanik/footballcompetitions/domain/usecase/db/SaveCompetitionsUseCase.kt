package com.romanik.footballcompetitions.domain.usecase.db

import com.romanik.footballcompetitions.data.mapper.CloudErrorMapper
import com.romanik.footballcompetitions.domain.model.Competition
import com.romanik.footballcompetitions.domain.repository.ICompetitionRepository
import com.romanik.footballcompetitions.domain.usecase.base.UseCase

class SaveCompetitionsUseCase(
    errorUtil: CloudErrorMapper,
    private val competitionRepository: ICompetitionRepository
) : UseCase<Unit>(errorUtil) {

    lateinit var competitions: List<Competition>

    override suspend fun executeOnBackground() {
        competitionRepository.saveCompetitions(competitions)
    }

}