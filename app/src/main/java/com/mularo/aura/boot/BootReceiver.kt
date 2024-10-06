package com.mularo.aura.boot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlin.random.Random


private const val TAG = "BootReceiver"

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.i(TAG, "onReceive: $intent")
            onBootEventReceived(context, intent)
        }
    }

    private fun onBootEventReceived(context: Context, intent: Intent) {

        //todo: save boot event counter in room db


    }
}