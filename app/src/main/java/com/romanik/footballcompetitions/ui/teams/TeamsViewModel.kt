package com.romanik.footballcompetitions.ui.teams

import androidx.lifecycle.MutableLiveData
import com.romanik.footballcompetitions.core.extentions.emptyString
import com.romanik.footballcompetitions.core.platform.BaseViewModel
import com.romanik.footballcompetitions.core.platform.Event
import com.romanik.footballcompetitions.domain.model.Team
import com.romanik.footballcompetitions.domain.usecase.FetchSaveTeamsUseCase

class TeamsViewModel(
    private val fetchSaveTeamsUseCase: FetchSaveTeamsUseCase
) : BaseViewModel() {
    override val TAG: String = TeamsViewModel::class.simpleName ?: String.emptyString()
    val teams: MutableLiveData<List<Team>> by lazy { MutableLiveData<List<Team>>() }

    init {
        doIt()
    }

    fun doIt() {
        loading.value = Event(true)
        fetchSaveTeamsUseCase.execute {
            onComplete {
                teams.value = it
                loading.value = Event(false)
            }

            onError {
                error.value = it
                loading.value = Event(false)
            }
        }
    }

}
