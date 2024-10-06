package com.mularo.aura.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.mularo.aura.R
import com.mularo.aura.utils.formatDate
import java.util.Date

private const val CHANNEL_ID = "channel_id"

private const val CHANNEL_NAME = "Channel"

class NotificationHandler {
    companion object {
        fun showNotification(context: Context, dates: List<Date>) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            //todo: implement notification dismiss action
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Title")
                .setContentText(getText(dates))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            notificationManager.notify(1, notification)
        }

        private fun getText(dates: List<Date>): String {
            return when (dates.size) {
                0 -> "No boots detected"
                1 -> "The boot was detected = ${formatDate(dates[0])}"
                else -> "Last boots time delta = ${dates[0].time - dates[1].time}"
            }
        }
    }
}