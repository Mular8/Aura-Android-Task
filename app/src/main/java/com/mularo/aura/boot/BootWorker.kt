package com.mularo.aura.boot

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mularo.aura.db.BootRepository
import com.mularo.aura.notification.NotificationHandler
import java.util.concurrent.TimeUnit

private const val WORK_NAME = "work_name"


class BootWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        NotificationHandler.showNotification(applicationContext,BootRepository.getDates())
        return Result.success()
    }


    companion object {
        fun scheduleWork(context: Context) {
            val workRequest = PeriodicWorkRequestBuilder<BootWorker>(15, TimeUnit.MINUTES)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }
    }
}