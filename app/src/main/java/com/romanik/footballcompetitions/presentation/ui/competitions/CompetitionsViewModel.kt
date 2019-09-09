package com.romanik.footballcompetitions.presentation.ui.competitions

import androidx.lifecycle.LiveData
import com.romanik.footballcompetitions.presentation.core.platform.BaseViewModel
import com.romanik.footballcompetitions.domain.model.Competition
import com.romanik.footballcompetitions.domain.usecase.db.FetchCompetitionsFromDataBaseUseCase
import com.romanik.footballcompetitions.presentation.core.extentions.emptyString
import com.romanik.footballcompetitions.presentation.core.platform.Event
import com.romanik.footballcompetitions.presentation.core.platform.SingleLiveEvent
import com.romanik.footballcompetitions.domain.usecase.cloud.FetchCompetitionsUseCase
import com.romanik.footballcompetitions.domain.usecase.db.SaveCompetitionsUseCase


class CompetitionsViewModel(
    private val fetchCompetitionsFromDataBaseUseCase: FetchCompetitionsFromDataBaseUseCase,
    private val fetchCompetitionsUseCaseCloud: FetchCompetitionsUseCase,
    private val saveCompetitionsUseCase: SaveCompetitionsUseCase
) : BaseViewModel() {

    enum class CompetitionsEvent {
        CLICK_ITEM, CHANGE_SCREEN, OBSERVE_DATA
    }

    override val TAG: String = CompetitionsViewModel::class.simpleName ?: String.emptyString()
    val event: SingleLiveEvent<Event<CompetitionsEvent>> by lazy { SingleLiveEvent<Event<CompetitionsEvent>>() }
    lateinit var competitions: LiveData<List<Competition>>

    fun fetchCompetitions() {
        loading.value = Event(true)
        fetchCompetitionsFromDataBaseUseCase.execute {
            onComplete {
                competitions = it
                event.value = Event(CompetitionsEvent.OBSERVE_DATA)
                loading.value = Event(false)
                fetchFromCloudAndSaveCompetitions()
            }

            onError {
                error.value = it
                loading.value = Event(false)
            }

            onCancel {
                loading.value = Event(false)
            }
        }
    }

    private fun fetchFromCloudAndSaveCompetitions() {
        fetchCompetitionsUseCaseCloud.execute {
            onComplete {
                saveCompetitions(it)
            }

            onError {
                error.value = it
            }
        }
    }

    private fun saveCompetitions(competitions: List<Competition>) {
        saveCompetitionsUseCase.competitions = competitions
        saveCompetitionsUseCase.execute {
            onError {
                error.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchCompetitionsFromDataBaseUseCase.unsubscribe()
        fetchCompetitionsUseCaseCloud.unsubscribe()
        saveCompetitionsUseCase.unsubscribe()
    }

}
