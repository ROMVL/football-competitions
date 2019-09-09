package com.romanik.footballcompetitions.di

import com.romanik.footballcompetitions.data.mapper.CloudErrorMapper
import com.romanik.footballcompetitions.data.repository.CompetitionRepository
import com.romanik.footballcompetitions.data.repository.TeamsRepository
import com.romanik.footballcompetitions.data.source.cloud.CloudRepository
import com.romanik.footballcompetitions.data.source.cloud.ICloudRepository
import com.romanik.footballcompetitions.domain.repository.ICompetitionRepository
import com.romanik.footballcompetitions.domain.repository.ITeamRepository
import com.romanik.footballcompetitions.domain.usecase.db.FetchCompetitionsFromDataBaseUseCase
import com.romanik.footballcompetitions.domain.usecase.FetchSaveTeamsUseCase
import com.romanik.footballcompetitions.domain.usecase.cloud.FetchCompetitionsUseCase
import com.romanik.footballcompetitions.domain.usecase.db.SaveCompetitionsUseCase
import com.romanik.footballcompetitions.presentation.ui.competitiondetails.CompetitionDetailsViewModel
import com.romanik.footballcompetitions.presentation.ui.competitions.CompetitionsViewModel
import com.romanik.footballcompetitions.presentation.ui.teams.TeamsViewModel
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val viewModelModule = module {
    viewModel { CompetitionsViewModel(get(), get(), get()) }
    viewModel { TeamsViewModel(get()) }
    viewModel { CompetitionDetailsViewModel() }
}

val mapperModule = module {
    single { CloudErrorMapper() }
}

val repositoryModule = module {
    single { CloudRepository(get()) as ICloudRepository }
    single { CompetitionRepository(get(), get()) as ICompetitionRepository }
    single { TeamsRepository(get(), get()) as ITeamRepository }
}

val useCaseModule = module {
    factory { SupervisorJob() }
    factory { FetchSaveTeamsUseCase(get(), get()) }
    factory { FetchCompetitionsUseCase(get(), get()) }
    factory { SaveCompetitionsUseCase(get(), get()) }
    factory { FetchCompetitionsFromDataBaseUseCase(get(), get()) }
}


val appModule = listOf(viewModelModule, mapperModule, repositoryModule, useCaseModule)