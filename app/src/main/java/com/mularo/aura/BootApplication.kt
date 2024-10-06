package com.mularo.aura

import android.app.Application
import com.mularo.aura.boot.BootWorker.Companion.scheduleWork


class BootApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        scheduleWork(this)
    }
}