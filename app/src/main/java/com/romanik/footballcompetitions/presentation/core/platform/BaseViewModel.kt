package com.romanik.footballcompetitions.presentation.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romanik.footballcompetitions.domain.model.response.ErrorModel

abstract class BaseViewModel : ViewModel() {

    abstract val TAG: String

    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val loading: SingleLiveEvent<Event<Boolean>> by lazy { SingleLiveEvent<Event<Boolean>>() }

}