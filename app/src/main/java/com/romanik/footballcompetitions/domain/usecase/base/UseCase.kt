package com.romanik.footballcompetitions.domain.usecase.base

import com.romanik.footballcompetitions.data.mapper.CloudErrorMapper
import com.romanik.footballcompetitions.domain.model.response.ErrorModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = UseCase.Request<T>.() -> Unit

abstract class UseCase<T>(private val errorUtil: CloudErrorMapper) {

    private var parentJob: Job = SupervisorJob()
    private var backgroundContext: CoroutineContext = Dispatchers.IO
    private var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(): T

    fun execute(block: CompletionBlock<T>) {
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            runCatching {
                withContext(backgroundContext) {
                    executeOnBackground()
                }
            }.onSuccess { result ->
                response(result)
            }.onFailure { error ->
                if (error is CancellationException) {
                    response(error)
                } else {
                    val errorModel = errorUtil.mapToDomainErrorException(error)
                    response(errorModel)
                }
            }
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    class Request<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onError: ((ErrorModel) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onError(block: (ErrorModel) -> Unit) {
            onError = block
        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }


        operator fun invoke(result: T) {
            onComplete?.invoke(result)
        }

        operator fun invoke(error: ErrorModel) {
            onError?.invoke(error)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }
    }
}