package com.examen.parrot.shared.framework

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*
import javax.inject.Inject

class Repository @Inject constructor()
class UploadWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val repository: Repository
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result = coroutineScope {
        val jobs = (0 until 100).map {
            async {
            }
        }

        // awaitAll will throw an exception if a download fails, which CoroutineWorker will treat as a failure
        jobs.awaitAll()
        Result.success()
    }


    @AssistedInject.Factory
    interface Factory : ChildWorkerFactory
    companion object {
        private const val TAG = "BisonWorker"
    }


}