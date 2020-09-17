package com.examen.parrot.shared.framework

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.examen.parrot.stores.interactors.GetStores
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class RefreshWorker @WorkerInject constructor(
    @Assisted context: Context, @Assisted params: WorkerParameters, private val getStores: GetStores
) : CoroutineWorker(context,params) {


    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {

        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        val currentDateAndTime: String = simpleDateFormat.format(Date())
        Log.d("Refreshing","Refreshing data $currentDateAndTime")


        val token = inputData.getString("Token") ?: ""
        getStores.getStoresRefresh(token)


        Result.success()
    }


}